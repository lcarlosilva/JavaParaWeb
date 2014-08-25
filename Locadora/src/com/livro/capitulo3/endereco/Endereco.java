package com.livro.capitulo3.endereco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;

import com.livro.capitulo3.cliente.Cliente;

@Entity
@Table(name="endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = -3359145874076528853L;

	@Id
	/*indica que a geração do valor da chave primaria de Endereço
	ficará por conta da foreign key 'FK_ENDERECO_COD_CLIENTE'*/
	@GeneratedValue(generator = "FK_ENDERECO_COD_CLIENTE")
	/*indica que quando um Cliente for salvo, o codigo gerado 
	por sua chave primária no banco será recuperado e utilizado
	pela classe Endereco.*/
	@org.hibernate.annotations.GenericGenerator(
			name="FK_ENDERECO_COD_CLIENTE", 
			strategy="foreign",
			parameters=@Parameter(
					name="property",
					value="cliente"
					)
	)
	@Column(name="cod_cliente")
	private Integer endereco;
	
	@OneToOne(mappedBy = "enderco")
	private Cliente cliente;
	
	private String rua;
	private Integer numero;
	private String bairro;
	private String cidade;
	
	@Column(name="estado")
	private String uf;
	private String cep;
	private String complemento;

	public Integer getEndereco() {
		return endereco;
	}

	public void setEndereco(Integer endereco) {
		this.endereco = endereco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
