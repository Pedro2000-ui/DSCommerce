package com.devsuperior.dscommerce.controllers.handlers;

import com.devsuperior.dscommerce.dto.CustomErrorDTO;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

/**
 * Classe responsável por centralizar o tratamento de exceções lançadas
 * pelos Controllers da aplicação.
 *
 * <p>O {@link ControllerAdvice} permite definir um tratamento global para
 * exceções que ocorrem durante o processamento das requisições HTTP.
 * Dessa forma, não é necessário colocar blocos {@code try-catch} em cada
 * método dos Controllers que possam lançar uma determinada exceção.</p>
 *
 * <p>Por exemplo, quando um recurso não é encontrado, o Service pode lançar
 * uma {@link ResourceNotFoundException}. Essa exceção sobe pela cadeia de
 * chamadas até chegar ao Spring, que identifica o método anotado com
 * {@link ExceptionHandler} e executa o tratamento correspondente.</p>
 *
 * <p>Com isso, a responsabilidade fica separada:</p>
 * <ul>
 *     <li>O Service identifica a situação excepcional e lança a exceção;</li>
 *     <li>O Controller fica responsável apenas por receber a requisição
 *     e delegar a operação;</li>
 *     <li>Esta classe é responsável por transformar a exceção em uma
 *     resposta HTTP adequada.</li>
 * </ul>
 *
 * <p>Essa abordagem evita duplicação de código e mantém os Controllers
 * mais limpos, além de padronizar o formato das respostas de erro da API.</p>
 *
 * <p>O {@link ExceptionHandler} define qual método deve ser executado
 * quando uma determinada exceção é lançada. Neste caso, o método
 * {@code resourceNotFound} trata especificamente exceções do tipo
 * {@link ResourceNotFoundException}, retornando o status HTTP
 * {@link HttpStatus#NOT_FOUND} (404).</p>
 *
 * @see ControllerAdvice
 * @see ExceptionHandler
 * @see ResourceNotFoundException
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Trata exceções do tipo {@link ResourceNotFoundException}.
     *
     * <p>Quando um recurso solicitado não é encontrado, o método cria um
     * {@link CustomErrorDTO} contendo informações sobre o erro e retorna
     * uma resposta HTTP com status 404 (Not Found).</p>
     *
     * @param e exceção lançada quando o recurso solicitado não é encontrado
     * @param request requisição HTTP que originou a exceção
     * @return resposta HTTP contendo o status 404 e os dados do erro
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request)
    {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorDTO err = new CustomErrorDTO(
                Instant.now(),
                status.value(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

}
