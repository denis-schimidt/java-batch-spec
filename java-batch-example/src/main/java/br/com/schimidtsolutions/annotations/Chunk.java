package br.com.schimidtsolutions.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Target({FIELD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Chunk {}
