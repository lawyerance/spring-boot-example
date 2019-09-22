package pers.lyks.spring.example.strategy

import com.google.common.collect.Lists
import org.springframework.beans.factory.BeanDefinitionStoreException
import org.springframework.context.ResourceLoaderAware
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.core.io.support.ResourcePatternUtils
import org.springframework.core.type.classreading.CachingMetadataReaderFactory
import org.springframework.core.type.classreading.MetadataReader
import org.springframework.core.type.classreading.MetadataReaderFactory
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.core.type.filter.TypeFilter
import org.springframework.util.ClassUtils
import org.springframework.util.StringUtils
import org.springframework.util.SystemPropertyUtils

import java.io.IOException
import java.util.HashSet

class ClassScanner : ResourceLoaderAware {
    private val includeFilters = Lists.newLinkedList<TypeFilter>()
    private val excludeFilters = Lists.newLinkedList<TypeFilter>()

    private var resourcePatternResolver: ResourcePatternResolver = PathMatchingResourcePatternResolver()
    private var metadataReaderFactory: MetadataReaderFactory = CachingMetadataReaderFactory(this.resourcePatternResolver)

    override fun setResourceLoader(resourceLoader: ResourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
        this.metadataReaderFactory = CachingMetadataReaderFactory(resourceLoader)
    }

    fun doScan(basePackage: String): Set<Class<*>> {
        val classes = HashSet<Class<*>>()
        try {
            val packageSearchPath = (ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage))
                    + "/**/*.class")
            val resources = this.resourcePatternResolver.getResources(packageSearchPath)
            for (resource in resources) {
                if (resource.isReadable) {
                    val metadataReader = this.metadataReaderFactory.getMetadataReader(resource)
                    if (includeFilters.size == 0 && excludeFilters.size == 0 || matches(metadataReader)) {
                        try {
                            classes.add(Class.forName(metadataReader.classMetadata.className))
                        } catch (e: ClassNotFoundException) {
                            e.printStackTrace()
                        }

                    }
                }
            }
        } catch (ex: IOException) {
            throw BeanDefinitionStoreException("I/O failure during classpath scanning", ex)
        }

        return classes
    }

    fun addIncluder(filter: TypeFilter) {
        this.includeFilters.add(filter)
    }

    fun addcExcluder(filter: TypeFilter) {
        this.excludeFilters.add(filter)
    }

    @Throws(IOException::class)
    protected fun matches(metadataReader: MetadataReader): Boolean {
        for (filter in this.excludeFilters) {
            if (filter.match(metadataReader, this.metadataReaderFactory)) {
                return false
            }
        }
        for (filter in this.includeFilters) {
            if (filter.match(metadataReader, this.metadataReaderFactory)) {
                return true
            }
        }
        return false
    }

    companion object {

        @SafeVarargs
        fun scan(basePackages: Array<String>, vararg annotations: Class<out Annotation>): Set<Class<*>> {
            val sc = ClassScanner()
            if (annotations != null) {
                for (annotation in annotations) {
                    sc.addIncluder(AnnotationTypeFilter(annotation))
                }
            }
            val classes = HashSet<Class<*>>()
            for (p in basePackages) {
                classes.addAll(sc.doScan(p))
            }
            return classes
        }

        @SafeVarargs
        fun scan(basePackages: String, vararg annotations: Class<out Annotation>): Set<Class<*>> {
            return ClassScanner.scan(StringUtils.tokenizeToStringArray(basePackages, ",; \t\n"), *annotations)
        }
    }
}
