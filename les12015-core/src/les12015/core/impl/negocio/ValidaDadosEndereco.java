package les12015.core.impl.negocio;

import les12015.core.IStrategy;
import les12015.dominio.Endereco;
import les12015.dominio.EntidadeDominio;

public class ValidaDadosEndereco implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Endereco) {
			Endereco end = (Endereco) entidade;

			String cep = end.getCep();
			String estado = end.getEstado();
			String cidade = end.getCidade();
			String obs = end.getObs();
			String tipo_res = end.getTipo_res();
			String tipo_log = end.getTipo_log();
			String logradouro = end.getLogradouro();
			String numero = end.getNumero();
			String bairro = end.getBairro();

			if (cep == null || estado == null || cidade == null || obs == null || tipo_res == null || tipo_log == null
					|| logradouro == null || numero == null || bairro == null) {
				return "Dados Cadastrais Obrigatorios";

			}

			if (cep.trim().equals("") || estado.trim().equals("") || cidade.trim().equals("") || obs.trim().equals("")
					|| tipo_res.trim().equals("") || tipo_log.trim().equals("") || bairro.trim().equals("")
					|| logradouro.trim().equals("") || numero.trim().equals("")) {
				return "Dados Cadastrais Obrigatorios";

			}

			if (cep.trim().length() > 10) {
				return "CEP com numeros de caracteres maior que o permitido";
			}
			if (estado.trim().length() > 2) {
				return "Estado Invalido";
			}
			if (cidade.trim().length() > 30) {
				return "Cidade com numeros de caracteres maior que o permitido";
			}
			if (obs.trim().length() > 255) {
				return "Observação passa do limite de caracteres";
			}
			if (tipo_res.trim().length() > 40) {
				return "Tipo Res com numeros de caracteres maior que o permitido";
			}
			if (tipo_log.trim().length() > 255) {
				return "Tipo Logradouro com numeros de caracteres maior que o permitido";
			}
			if (numero.trim().length() > 3) {
				return "Numero com numeros de caracteres maior que o permitido";
			}
			if (bairro.trim().length() > 250) {
				return "Bairro com numeros de caracteres maior que o permitido";
			}

		} else

		{
			return "Registro de Endereco";
		}

		return null;
	}

}
