package br.com.lucasdev3.financesystemapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {

  private String message;
  private Object obj;

  public ResponseModel(String message) {
    this.message = message;
  }
}
