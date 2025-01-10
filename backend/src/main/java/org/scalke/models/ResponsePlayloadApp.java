package org.scalke.models;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePlayloadApp extends ResponseApp{
   private Map<String, Object> playload;
}
