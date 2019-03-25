package les12015.core.impl.negocio;

import java.util.Calendar;
import java.util.GregorianCalendar;

import les12015.core.IStrategy;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Suplementos;

public class ValidaValidade implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Suplementos) {
			Suplementos sup = (Suplementos) entidade;
			GregorianCalendar calendar = new GregorianCalendar();
			
			if(sup.getValidade().equals("vazio")){
				return "Data de validade é obrigatorio";
			}

			int ano = Integer.parseInt(sup.getValidade().substring(0, 4));
			int mes = Integer.parseInt(sup.getValidade().substring(5, 7));
			int dia = Integer.parseInt(sup.getValidade().substring(8, 10));
			// 2019-03-09
			
			if (mes > 12)
				return "Digite um mes valido";
			if (ano > 9999)
				return "Digite um Ano Valido";

			if (ano < calendar.get(Calendar.YEAR))
				return "O Produto ja está vencido";

			if (mes < calendar.get(Calendar.MONTH) && ano < calendar.get(Calendar.YEAR))
				return "O Produto ja está vencido";

			if (dia < calendar.get(Calendar.DAY_OF_MONTH) && mes < calendar.get(Calendar.MONTH))
				return "O Produto ja está vencido";


		} else {
			return "Registro de Suplementos";
		}

		return null;
	}
}
