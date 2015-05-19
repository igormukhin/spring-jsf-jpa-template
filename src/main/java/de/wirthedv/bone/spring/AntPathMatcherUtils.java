package de.wirthedv.bone.spring;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class AntPathMatcherUtils {
    private static final String PATTERN_SEPARATOR = ";";

    private static final AntPathMatcher PATHMATCHER = new AntPathMatcher();
    
    public static final String MATCH_ALL_TOPLEVEL = "*";
    public static final String MATCH_ALL_RECURSIVELY = "**";
    
    /**
     * Uses AntPathMatcher to match paths against the pattern. The difference is that this method allows
     * to use multiple Ant patterns divided by ";".  
     * 
     * <p>Examples: "/users;/users/**", "/profile;/profile/**", "/;/book;/book/**"</p>
     * 
     * @see org.springframework.util.AntPathMatcher
     */
    public static boolean matches(String pattern, String path) {
        Assert.notNull(pattern, "pattern");
        Assert.notNull(path, "path");
        
        String[] patterns = StringUtils.delimitedListToStringArray(pattern, PATTERN_SEPARATOR);
        for (String onePattern: patterns) {
            onePattern = onePattern.trim();
            if (PATHMATCHER.match(onePattern, path)) {
                return true;
            }
        }
        
        return false;
    }
}
