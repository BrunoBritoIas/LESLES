package les12015.core.impl.negocio;

import les12015.core.IStrategy;
import les12015.dominio.Cliente;
import les12015.dominio.EntidadeDominio;

public class ValidaDadosCliente implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Cliente) {
			Cliente cli = (Cliente) entidade;

			String nome = cli.getNome();
			String email = cli.getEmail();
			String senha = cli.getSenha();
			String telefone = cli.getTelefone();
			String genero = cli.getGenero();
			String idade = cli.getDtNasc();

			if (nome == null || email == null || senha == null || telefone == null || genero == null || idade == null) {
				return "Dados Cadastrais Obrigatorios";

			}

			if (nome.trim().equals("") || email.trim().equals("") || senha.trim().equals("")
					|| telefone.trim().equals("") || genero.trim().equals("") || idade.trim().equals("")) {
				return "Dados Cadastrais Obrigatorios";

			}
			
			if( nome.trim().length()>100 ) {
				return "Nome com numeros de caracteres maior que o permitido";
			}
			if( email.trim().length()>100 ) {
				return "Email com numeros de caracteres maior que o permitido";
			}
			if( senha.trim().length()>30 ) {
				return "Senha com numeros de caracteres maior que o permitido";
			}
			if( senha.trim().length()<6 ) {
				return "Senha Fraca";
			}
			if( telefone.trim().length()>11 ) {
				return "Telefone Invalido";
			}
			if( telefone.trim().length() < 10 ) {
				return "Número de telefone invalido";
			}
			
		} else

		{
			return "Registro de Cliente";
		}

		return null;
	}

}
