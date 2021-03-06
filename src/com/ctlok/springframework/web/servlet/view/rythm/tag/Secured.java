package com.ctlok.springframework.web.servlet.view.rythm.tag;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.rythmengine.template.JavaTagBase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Lawrence Cheung
 *
 */
public class Secured extends JavaTagBase {

    @Override
    public String __getName() {
        return "secured";
    }
    
    @Override
    protected void call(__ParameterList params, __Body body) {
        
        if (body != null){
        
            final Set<String> authorities = getUserAuthorities();
            
            for (final Iterator<__Parameter> iterator = params.iterator(); iterator.hasNext();){
                
                final __Parameter parameter = iterator.next();
                
                if (authorities.contains(parameter.value)){
                    
                    this.p(body.render());
                    
                }
                
            }
        
        }
        
    }
    
    protected Set<String> getUserAuthorities(){
        final Set<String> authorities = new HashSet<String>();
        final SecurityContext context = SecurityContextHolder.getContext();
        
        if (context != null){
            
            final Authentication auth= context.getAuthentication();
            
            if (auth != null){
                
                for (final GrantedAuthority authority: auth.getAuthorities()){
                    authorities.add(authority.getAuthority());
                }
                
            }
        }
        
        return authorities;
    }

}
