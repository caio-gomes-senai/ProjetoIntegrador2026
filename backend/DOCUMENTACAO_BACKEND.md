# Como o nosso Backend funciona (Explicado de um jeito fácil)

Beleza, se eu esquecer como essa bagunça funciona, aqui tá o resumo pra eu não me perder:

---

## 1. Onde fica cada coisa? (Pastas)

- **model:** É onde a gente fala pro banco de dados como as tabelas devem ser. "Ó, o Produto tem nome e preço".
- **repository:** É a ponte com o banco. A gente não escreve SQL na mão, o Spring faz pra gente.
- **service:** É o "cérebro". Se tiver que fazer alguma conta, validação ou regra doida, é aqui que fica. O Controller não deve pensar, quem pensa é o Service.
- **controller:** É a porta da rua. Quando alguém clica num botão no site, a requisição bate aqui primeiro.
- **dto:** Sabe quando você não quer mostrar tudo do banco? O DTO é tipo uma "caixinha" só com o que a gente quer mandar ou receber do site.
- **templates:** São as nossas páginas (HTML). É o que o usuário vê.

---

## 2. O caminho de um clique (CRUD)

Imagina que eu quero cadastrar um **Produto**:

1.  **O Clique:** Eu preencho o formulário no site e clico em "Salvar".
2.  **A Chegada (Controller):** O `WebProdutoController` recebe esses dados. Ele não sabe o que fazer com eles, então ele só passa a bola pro Service.
3.  **A Lógica (Service):** O `ProdutoService` pega os dados, vê se tá tudo certo, transforma o que precisa e fala pro Repository: "Aí, guarda isso pra mim".
4.  **O Banco (Repository):** O Repository salva lá no MySQL e pronto.
5.  **A Resposta:** O site te avisa que deu certo ou te manda de volta pra lista.

**Pra Deletar/Editar é a mesma lógica:**
- O Controller recebe o ID do que você quer mexer.
- Avisa o Service.
- O Service manda o Repository apagar ou mudar no banco.

---

## 3. Quem depende de quem? (Regras Práticas)

Pra não dar erro na hora de cadastrar as coisas, se liga nessa ordem:

1.  **Usuário:** É o primeirão. Sem usuário cadastrado, nada do resto acontece.
2.  **Freezer:** Só dá pra criar se o **Usuário** já existir (pra ele ser o dono).
3.  **Sensor:** Só dá pra criar se o **Freezer** já existir (pra saber onde o sensor tá instalado).
4.  **Produto:** Você pode criar o produto sozinho, mas ele só serve pra ser monitorado se você colocar ele dentro de um **Freezer**.
5.  **Histórico:** Esse é automático, mas ele só nasce se tiver um **Sensor** mandando a temperatura.

**Resumo da linha do tempo:**
`Usuário` -> `Freezer` -> `Sensor` -> `Histórico`
(E o `Produto` entra no `Freezer` no meio do caminho).

---

## 4. Por que tanto arquivo pra uma coisa só?

Parece frescura, mas é pra não virar bagunça:
- Se der erro no banco, eu vou no **Repository**.
- Se a conta tiver errada, eu vou no **Service**.
- Se o link não funcionar, eu vou no **Controller**.
- Se o desenho da tabela mudar, eu vou no **Model**.

---

## 5. O que a gente tá usando?

- **Java & Spring:** O motor de tudo.
- **MySQL:** Onde os dados moram de verdade.
- **Thymeleaf:** O que faz o HTML conversar com o Java.

---

## 6. Como criar um CRUD novo (Passo a passo)

Se você precisar criar uma tela nova (tipo pra "Caminhões" ou "Clientes"), faz sempre nessa ordem pra não se embolar:

1.  **Cria o Model:** Cria a classe na pasta `model` e diz o que ela tem (id, nome, etc). Não esquece das anotações `@Entity` e `@Data`.
2.  **Cria o Repository:** Cria a interface na pasta `repository`. É só copiar uma que já existe e mudar o nome.
3.  **Cria o Service:** Cria na pasta `service`. Faz os métodos básicos: `findAll`, `save`, `findById` e `delete`.
4.  **Cria o Controller:**
    *   Se for só pra API, usa `@RestController`.
    *   Se for pra ter tela, usa `@Controller` e cria os `@GetMapping` pra abrir a página e o `@PostMapping` pra salvar.
5.  **Cria as Telas:** Vai na pasta `templates` e cria o HTML (ex: `clienteLista.html` e `clienteCadastro.html`). Pega um que já existe e troca as variáveis.

**Dica:** Tenta sempre usar o **DTO** pra mandar os dados pro site, assim você não expõe sua senha ou dados sensíveis que estão no Model.

---

**Dica final pra mim mesmo:** Na dúvida, olha o que a gente fez no **Produto**. Ele é o melhor exemplo de como tudo se conecta!
