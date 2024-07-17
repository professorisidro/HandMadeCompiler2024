# Compiladores Q2 2024
Especificação da Linguagem
- Expressões aritmeticas que permitam
	- somas, subtrações, multiplicações e divisões
	- números inteiros
	- nomes de variáveis
	- pontuação de final de linha

## Tokens a serem reconhecidos
- numeros inteiros : (0-9)+
- identificadores  : (A-Z|a-z) (A-Z|a-z|0-9)*
- operadores       : + | - | * | / | ^
- pontuação        : ; | , | .
- espaços em branco: ' ' | \n | \r | \t
- palavras reservadas
	BEGIN
	END
	PROGRAM
	VAR
	INTEGER

## Automato
![Imagem do Automato](https://github.com/professorisidro/HandMadeCompiler2024/blob/main/Automato_Linguagem.png)

## Requisitos não funcionais
	- tem que ler de arquivo a expressão de entrada
	- linguagem é case-sensitive

## Legenda do autômato
- \+   1 ou mais repetições (Fecho positivo)
- \*   0 ou mais repetições
- ?   0 ou 1 ocorrência
