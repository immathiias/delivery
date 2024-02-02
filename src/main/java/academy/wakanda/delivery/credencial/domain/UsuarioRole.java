package academy.wakanda.delivery.credencial.domain;

public enum UsuarioRole {
    ADMIN("admin"),
    CLIENTE("cliente");

    private String role;

    UsuarioRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
