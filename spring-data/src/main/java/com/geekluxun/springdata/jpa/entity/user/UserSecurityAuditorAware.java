package com.geekluxun.springdata.jpa.entity.user;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-12-05 17:46
 * @Description: 这个Aware提供了对 @CreatedBy和@@LastModifiedBy的支持
 * @Other:
 */
@Component
class UserSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        /**
         * 这里提供了一个安全访问策略
         */
//        return Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(Authentication::getPrincipal)
//                .map(String.class::cast);
        return Optional.of("geekluxun");
    }
}