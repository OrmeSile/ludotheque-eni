  import { HttpInterceptorFn } from '@angular/common/http';
import {inject} from '@angular/core';
import {SecurityStore} from '../shared/security/security-store.service';

export const securityInterceptor: HttpInterceptorFn = (req, next) => {
  const keycloakService = inject(SecurityStore);

  const bearer = keycloakService.user()?.bearer;

  console.log("passed in interceptor ?")

  if (!bearer) {
    return next(req);
  }

  return next(
    req.clone({
      headers: req.headers.set("Authorization", `Bearer ${bearer}`),
    })
  );
};
