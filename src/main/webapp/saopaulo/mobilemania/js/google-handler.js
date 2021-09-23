      const scriptURL = 'https://script.google.com/macros/s/AKfycbzWy6t30LNje7tjO_Hk28xOOlmqdR-z1_1QvPhwG5v5zHwvBXArPfuUziGyMUO8rfLy/exec'
            const form = document.forms['google-sheet']

            form.addEventListener('submit', e => {
              e.preventDefault()
              fetch(scriptURL, { method: 'POST', body: new FormData(form)})
             
            })


 // CEP

$("#cep").blur(function(){
				// Remove tudo o que não é número para fazer a pesquisa
				var cep = this.value.replace(/[^0-9]/, "");
				
				// Validação do CEP; caso o CEP não possua 8 números, então cancela
				// a consulta
				if(cep.length != 8){
					return false;
				}
				
				// A url de pesquisa consiste no endereço do webservice + o cep que
				// o usuário informou + o tipo de retorno desejado (entre "json",
				// "jsonp", "xml", "piped" ou "querty")
				var url = "https://viacep.com.br/ws/"+cep+"/json/";
				
				// Faz a pesquisa do CEP, tratando o retorno com try/catch para que
				// caso ocorra algum erro (o cep pode não existir, por exemplo) a
				// usabilidade não seja afetada, assim o usuário pode continuar//
				// preenchendo os campos normalmente
				$.getJSON(url, function(dadosRetorno){
					try{
						// Preenche os campos de acordo com o retorno da pesquisa
						$("#rua").val(dadosRetorno.logradouro);
						$("#bairro").val(dadosRetorno.bairro);
						$("#cidade").val(dadosRetorno.localidade);
						$("#estado").val(dadosRetorno.uf);
					}catch(ex){}
				});
			});