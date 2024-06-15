package br.com.cidadao.api.candidato_api.dto.auth;

public class JwtResponseDTO {
    private String accessToken;

    public JwtResponseDTO() {
    }

    public JwtResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
