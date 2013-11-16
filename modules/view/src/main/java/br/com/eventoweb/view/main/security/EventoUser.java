package br.com.eventoweb.view.main.security;

import org.picketlink.idm.impl.api.model.SimpleUser;

import br.com.eventoweb.domain.parametros.Usuario;

public class EventoUser extends SimpleUser {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8657211832452084232L;

	private final Usuario usuario;

    /* Permissões dos Menus de Perfil */
    private Boolean menuPerfil;
    private Boolean subMenuPerfil;
    private Boolean subMenuEventos;

    /* Permissões dos Menus de Perfil */
    private Boolean menuEventos;
    private Boolean subMenuCriarEvento;
    private Boolean subMenuPesquisarEvento;
    
    public EventoUser(Usuario usuario) {
        super(usuario.getId().toString());
        this.usuario = usuario;
        
        this.menuPerfil = true;
        this.subMenuPerfil = true;
        this.subMenuEventos = true;
        
        this.menuEventos = true;
        this.subMenuCriarEvento = this.usuario.getOrganizador();
        this.subMenuPesquisarEvento = true;
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public Boolean getMenuPerfil() {
		return menuPerfil;
	}

	public Boolean getSubMenuPerfil() {
		return subMenuPerfil;
	}

	public Boolean getMenuEventos() {
		return menuEventos;
	}

	public Boolean getSubMenuCriarEvento() {
		return subMenuCriarEvento;
	}

	public Boolean getSubMenuPesquisarEvento() {
		return subMenuPesquisarEvento;
	}

	public Boolean getSubMenuEventos() {
		return subMenuEventos;
	}

	
}
