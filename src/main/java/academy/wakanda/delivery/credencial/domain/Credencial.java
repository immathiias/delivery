package academy.wakanda.delivery.credencial.domain;

import java.util.Collection;
import java.util.List;

import academy.wakanda.delivery.handler.APIException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Log4j2
@Document(collection = "Credencial")
public class Credencial implements UserDetails {

    @Id
    @Getter
    private String email;
    @NotNull
    @Size(max = 60)
    private String senha;
    private UsuarioRole role;
    @Getter
    private boolean validado;

    public Credencial(String email, @NotNull String senha) {
        this.email = email;
        this.senha = encriptaSenha(senha);
        this.role = UsuarioRole.CLIENTE;
        this.validado = true;
    }

    public String encriptaSenha(String senha) {
        var encriptador = new BCryptPasswordEncoder();
        return encriptador.encode(senha);
    }

    public void validaCredencial() {
        this.validado = true;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_CLIENTE"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"), new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private static final long serialVersionUID = 1L;

}
