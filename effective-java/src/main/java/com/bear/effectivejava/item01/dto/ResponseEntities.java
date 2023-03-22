package com.bear.effectivejava.item01.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResponseEntities {

  private ResponseEntities() {
    throw new UnsupportedOperationException();
  }

  public static ResponseEntity<Void> created(final Long seq) {
    return ResponseEntity.created(
        ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(seq)
            .toUri()
    ).build();
  }

}
