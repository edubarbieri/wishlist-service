# User wishlist service
## Requisitos
- CRUD de clientes
  - Nome
  - Endereço de email - deve ser unico
- Lista de produtos
  - Cada client pode ter uma lista de produtos favoritos
  - Quantidade ilimitada de produtos
  - Um produto não pode ser adicionado caso ele não exista
  - Um produto não pode estar duplicado na lista de favoritos
    - Doc api de produtos [a link](https://gist.github.com/Bgouveia/9e043a3eba439489a35e70d1b5ea08ec)
    - 
- Serviço de renderização da lista:
  - Título, Imagem, Preço e irá utilizar o ID do produto
  - Quando existir um review para o produto, o mesmo será exibido por este dispositivo.
- O acesso à api deve ser aberto ao mundo, porém deve possuir autenticação
  e autorização.
- 