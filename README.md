# 🧮 Projeto PBL – Tabela Hash

Este projeto foi desenvolvido como parte da disciplina **Resolução de Problemas em Engenharia de Computação (RPEC)** na **Pontifícia Universidade Católica do Paraná (PUCPR)**, com foco na **implementação, análise e comparação de funções hash** utilizando uma **estrutura de tabela hash personalizada** em Java.

---

## 👨‍💻 Autores
- **Eduardo Lourenço da Silva**
- **Hector Vieira Saldivar**
- **Theo Rocha Otto**

---

## 📘 Descrição Geral

O projeto implementa uma **tabela hash genérica** com tratamento de colisões via **Encadeamento Exterior (Separate Chaining)**, utilizando uma estrutura de lista dinâmica personalizada chamada `EArrayList`.  
O objetivo principal foi **analisar o comportamento de diferentes funções hash**, observando:
- Distribuição das chaves;
- Taxa de colisões;
- Desempenho em tempo de inserção e busca.

---

## 🧩 Estrutura e Tratamento de Colisões

- **Método:** Encadeamento Exterior (listas dinâmicas em cada bucket).  
- **Motivação:** Simplicidade de implementação e reutilização da classe `EArrayList`.  
- **Fator de carga (Load Factor):**
  - Valor inicial: **5** (intencionalmente alto, para gerar mais colisões e facilitar a análise).  
  - Valor recomendado em aplicações reais: **≈ 0,75**.

---

## ⚙️ Funções Hash Implementadas

### 🔹 Função 1 – Baseada no Tamanho da Palavra
```java
index = key.length();
```

#### Resultados:
| Métrica | Valor |
|----------|--------|
| Total de inserções | 5001 |
| Colisões registradas | 4987 (99,72%) |
| Maior bucket | 1258 chaves (posição 5) |
| Tempo de inserção | 12 ms |
| Tempo de busca | 12,5 ms |

🧠 **Análise:**  
A distribuição foi extremamente concentrada — várias palavras com o mesmo comprimento colidem no mesmo índice.  
Mesmo com fator de carga menor, a função se manteve ineficiente.

---

### 🔹 Função 2 – Baseada em `hashCode()`
```java
int h = key.hashCode();
h ^= (h >>> 16);
index = h & (capacity - 1);
```

#### Resultados:
| Métrica | Valor |
|----------|--------|
| Total de inserções | 5001 |
| Colisões registradas | 4809 (96,16%) |
| Maior bucket | 13 chaves (posição 250) |
| Buckets vazios | 10 (1,0%) |
| Tempo de inserção | 2,368 ms |
| Tempo de busca | 0,539 ms |

🧠 **Análise:**  
A distribuição foi muito mais uniforme.  
A melhoria de desempenho foi significativa — cerca de **80% mais rápida nas inserções** e **95% mais rápida nas buscas** em relação à função simples.

---

## ⚖️ Teste com Fator de Carga Reduzido (0,75)

Com um **fator de carga ideal (0,75)**, a Tabela Hash aumentou de tamanho mais cedo, evitando acúmulo excessivo de elementos nos mesmos buckets.

### Comparativo:
| Métrica | Função 1 | Função 2 | Função 2 (fator 0,75) |
|----------|-----------|-----------|------------------------|
| Itens inseridos | 5001 | 5001 | 5001 |
| Colisões registradas | 4987 (99,72%) | 4809 (96,16%) | 2084 (41,67%) |
| Maior bucket | 1258 chaves | 13 chaves | 5 chaves |
| Buckets vazios | 1010 (98,16%) | 10 (1,0%) | 4455 (54,4%) |
| Tempo de inserção | 12 ms | 2,368 ms | 2,151 ms |
| Tempo de busca | 12,5 ms | 0,539 ms | 0,596 ms |

🧠 **Conclusão:**  
Um bom **projeto de função hash** tem impacto **muito maior** na eficiência da estrutura do que apenas ajustar o fator de carga.

---

## 🖥️ Ambiente de Testes

| Componente | Especificação |
|-------------|---------------|
| Sistema Operacional | Windows 11 (23H2) |
| Processador | Intel Core i5-12450H (12ª Geração, 2.00GHz) |
| Memória RAM | 12 GB |

---

## 🧾 Conclusão

O projeto demonstra que:
- O tratamento de colisões via **encadeamento exterior** é eficaz e simples de implementar;
- A **função hash baseada em `hashCode()` com mistura de bits** proporciona melhor desempenho e distribuição;
- O **fator de carga ideal (~0,75)** reduz colisões sem aumentar excessivamente o uso de memória.

Esses resultados reforçam a importância de **um bom design da função hash** para garantir eficiência em tabelas de dispersão.

---

---

## 📜 Licença
Este projeto foi desenvolvido exclusivamente para fins **acadêmicos**, como parte da disciplina de **RPEC – PBL Tabela Hash** (PUCPR, 2025).
