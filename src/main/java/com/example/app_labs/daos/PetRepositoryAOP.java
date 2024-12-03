package com.example.app_labs.daos;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PetRepositoryAOP {
    @Before("execution(* com.example.app_labs.daos.PetRepository.updatePetById(..))")
    public void logUpdatePetById(JoinPoint joinPoint) {

    }
}
