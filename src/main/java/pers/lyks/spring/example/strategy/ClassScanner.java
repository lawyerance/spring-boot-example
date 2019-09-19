package pers.lyks.spring.example.strategy;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassScanner implements ResourceLoaderAware {
    private final List<TypeFilter> includeFilters = Lists.newLinkedList();
    private final List<TypeFilter> excludeFilters = Lists.newLinkedList();

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);

    @SafeVarargs
    public static Set<Class<?>> scan(String[] basePackages, Class<? extends Annotation>... annotations) {
        ClassScanner sc = new ClassScanner();
        if (annotations != null) {
            for (Class annotation : annotations) {
                sc.addIncluder(new AnnotationTypeFilter(annotation));
            }
        }
        Set<Class<?>> classes = new HashSet<>();
        for (String p : basePackages) {
            classes.addAll(sc.doScan(p));
        }
        return classes;
    }

    @SafeVarargs
    public static Set<Class<?>> scan(String basePackages, Class<? extends Annotation>... annotations) {
        return ClassScanner.scan(StringUtils.tokenizeToStringArray(basePackages, ",; \t\n"), annotations);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

    public Set<Class<?>> doScan(String basePackage) {
        Set<Class<?>> classes = new HashSet<>();
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage))
                    + "/**/*.class";
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    if ((includeFilters.size() == 0 && excludeFilters.size() == 0)
                            || matches(metadataReader)) {
                        try {
                            classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException ex) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
        }
        return classes;
    }

    public void addIncluder(TypeFilter filter) {
        this.includeFilters.add(filter);
    }

    public void addcExcluder(TypeFilter filter) {
        this.excludeFilters.add(filter);
    }

    protected boolean matches(MetadataReader metadataReader) throws IOException {
        for (TypeFilter filter : this.excludeFilters) {
            if (filter.match(metadataReader, this.metadataReaderFactory)) {
                return false;
            }
        }
        for (TypeFilter filter : this.includeFilters) {
            if (filter.match(metadataReader, this.metadataReaderFactory)) {
                return true;
            }
        }
        return false;
    }
}
