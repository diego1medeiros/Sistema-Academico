package br.com.sistemaacademico.enun;

public enum Perfil {
	

		ADMIN("ADMIN"),
		FUNCIONARIO("FUNCIONARIO");

		private Perfil (String descricao) {
			this.descricao = descricao;
		}

		private String descricao;

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public static String[] getDescricaoPerfil() {
			String[] listaRetorno = new String[2];
			int i = 0;
			for (Perfil perfil : Perfil.values()) {
				listaRetorno[i] = perfil.getDescricao();
				i++;
			}
			return listaRetorno;
		}

	}

