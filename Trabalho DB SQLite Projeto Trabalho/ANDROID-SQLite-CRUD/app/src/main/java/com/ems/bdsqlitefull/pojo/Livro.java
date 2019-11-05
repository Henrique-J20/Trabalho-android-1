package com.ems.bdsqlitefull.pojo;

import java.io.Serializable;

// POJO - Plain Old Java Objects

public class Livro implements Serializable {

    private String nome;
    private String autor;
    private int faixa;
    private String editora;
    private String genero;

        /**
         * Método construtor vazio
         */
        public Livro() {
        }

        /**
         * Método construtor da classe com assinatura
         *
         * @param nome
         * @param autor
         * @param faixa
         * @param editora
         * @param genero
         */
        public Livro(String nome, String autor, int faixa, String editora, String genero) {
            this.nome = nome;
            this.autor = autor;
            this.faixa = faixa;
            this.editora = editora;
            this.genero = genero;
        }

        /**
         //     * Método construtor da classe com assinatura
         //     *
         //     * @param nome
         //     * @param autor
         //     * @param faixa
         //     * @param editora
         //     * @param genero
         //     */
//    public Livro(String nome, String autor, int faixa, String editora, String genero) {
//     this.nome = nome;
//     this.autor =



        // Getters and Setters

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public int getFaixa() {
            return faixa;
        }

        public void setFaixa(int faixa) {
            this.faixa = faixa;
        }

        public String getEditora() {
            return editora;
        }

        public void setEditora(String editora) {
            this.editora = editora;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        /**
         * Método sobrescrito para retornar o nome do livro na ListView
         *
         * @return
         */
        @Override
        public String toString() {
            return nome;
        }

        /**
         * Método que retorna todos os dados de uma só vez
         *
         * @return
         */
        public String getDados() {
            return "NOME: " + nome + "\n" +
                    "AUTOR: " + autor + "\n" +
                    "FAIXA: " + faixa + "\n" +
                    "EDITORA: " + editora + "\n" +
                    "GENERO: " + genero;
        }
    }

