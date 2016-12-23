package com.proxiad.extranet.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

/**
 * Redirects every page to index.html Used to handle the router
 */
@Configuration
public class SinglePageAppConfig extends WebMvcConfigurerAdapter {
	
	private static final String HOME_PAGE = "index.html";
	private static final String RESOURCES_PATH = "/static/";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static").resourceChain(false).addResolver(new PushStateResourceResolver());
	}

	private class PushStateResourceResolver implements ResourceResolver {
		private Resource index = new ClassPathResource("/static/index.html");
		private List<String> handledExtensions = Arrays.asList("html", "js", "json", "csv", "css", "png", "svg", "eot", "ttf", "woff", "appcache", "jpg", "jpeg", "gif", "ico", "pdf");
		private List<String> ignoredPaths = Arrays.asList("api/");

		@Override
		public Resource resolveResource(HttpServletRequest request, String requestPath, List<? extends Resource> locations, ResourceResolverChain chain) {
			return resolve(requestPath);
		}

		@Override
		public String resolveUrlPath(String resourcePath, List<? extends Resource> locations, ResourceResolverChain chain) {
			Resource resolvedResource = resolve(resourcePath);
			
			if (resolvedResource == null) {
				return null;
			}
			
			try {
				return resolvedResource.getURL().toString();
			} catch (IOException e) {
				return resolvedResource.getFilename();
			}
		}

		private Resource resolve(String requestPath) {
			if (isIgnored(requestPath)) {
				return null;
			}
			if (isHandled(requestPath)) {
				Resource finalLocation = new ClassPathResource(RESOURCES_PATH + requestPath);
				if (finalLocation.exists()) { 
					return finalLocation;
				} else {
					return null;
				}
			}
			return index;
		}


		private boolean isIgnored(String path) {
			for (String ignorePath: ignoredPaths) {
				if (path.contains(ignorePath)) {
					return true;
				}
			}
			
			return false;
		}

		private boolean isHandled(String path) {
			if (HOME_PAGE.equals(path)) {
				return false;
			}
			
			String extension = StringUtils.getFilenameExtension(path);
			
			for (String handledExtension : handledExtensions) {
				if (handledExtension.equals(extension)) {
					return true;
				}
			}
			
			return false;
		}
	}
}