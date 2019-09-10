/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pamcary.validacao;

import java.util.InputMismatchException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Clairton Luz - clairton.c.l@gmail.com
 * https://clairtonluz.github.io/blog/2014/07/2014072299.html
 * 
 */
public class CPFCNPJValidator implements ConstraintValidator<CPFCNPFJ, String> {

    @Override
    public void initialize(CPFCNPFJ constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean f =  value == null || value.isEmpty() || isCpf(value) || isCnpj(value);
        return f;
    }

    /**
     * Realiza a valida��o do CPF.
     *
     * @param cpf n�mero de CPF a ser validado pode ser passado no formado
     * 999.999.999-99 ou 99999999999
     * @return true se o CPF � v�lido e false se n�o � v�lido
     */
    private boolean isCpf(String cpf) {
    	
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }
        
     // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999") || (cpf.length() != 11)) {
            return (false);
        }
        
        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

            // multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
            // e assim por diante.
            d1 = d1 + (11 - nCount) * digitoCPF;

            // para o segundo digito repita o procedimento incluindo o primeiro
            // digito calculado no passo anterior.
            d2 = d2 + (12 - nCount) * digitoCPF;
        };

        // Primeiro resto da divis�o por 11.
        resto = (d1 % 11);

        // Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11
        // menos o resultado anterior.
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        // Segundo resto da divis�o por 11.
        resto = (d2 % 11);

        // Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o digito � 11
        // menos o resultado anterior.
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        // Digito verificador do CPF que est� sendo validado.
        String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

        // Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        // comparar o digito verificador do cpf com o primeiro resto + o segundo
        // resto.
        return nDigVerific.equals(nDigResult);
    }

    /**
     * Realiza a valida��o de um cnpj
     *
     * @param cnpj String - o CNPJ pode ser passado no formato
     * 99.999.999/9999-99 ou 99999999999999
     * @return boolean
     */
    private boolean isCnpj(String cnpj) {
        cnpj = cnpj.replace(".", "");
        cnpj = cnpj.replace("-", "");
        cnpj = cnpj.replace("/", "");

        try {
            Long.parseLong(cnpj);
        } catch (NumberFormatException e) {
            return false;
        }

        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999") || (cnpj.length() != 14)) {
            return (false);
        }
        char dig13, dig14;
        int sm, i, r, num, peso; // "try" - protege o c�digo para eventuais
        // erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-�simo caractere do CNPJ em um n�mero: // por
                // exemplo, transforma o caractere '0' no inteiro 0 // (48 eh a
                // posi��o de '0' na tabela ASCII)
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }
            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }
            // Verifica se os d�gitos calculados conferem com os d�gitos
            // informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
}
