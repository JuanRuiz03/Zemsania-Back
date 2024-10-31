package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client")
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ClientController {

    /**
     * Endpoint para obtener información básica del cliente basado en el tipo y número de documento.
     *
     * @param type           Tipo de documento del cliente ("C" para cédula o "P" para pasaporte).
     * @param documentNumber Número de documento del cliente.
     * @return ResponseEntity con la información del cliente si existe y es válida, o un mensaje de error.
     */
    @GetMapping("/info")
    public ResponseEntity<Object> getClientInfo(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String documentNumber) {

        // Verificar si los parámetros obligatorios están presentes
        if (type == null || documentNumber == null) {
            return new ResponseEntity<>("Missing type or document number", HttpStatus.BAD_REQUEST);
        }

        // Validar el tipo de documento
        if (!type.equalsIgnoreCase("C") && !type.equalsIgnoreCase("P")) {
            return new ResponseEntity<>("Invalid document type", HttpStatus.BAD_REQUEST);
        }


        // Datos mockeados .Estos datos son los que apareceran en la repsuesta de la peticion y podran ser usados en el front
        if (type.equalsIgnoreCase("C") && documentNumber.equalsIgnoreCase("23445322")) {
            Map<String, String> clientData = new HashMap<>();
            clientData.put("firstName", "Nicole");
            clientData.put("secondName", "Tatiana");
            clientData.put("firstSurname", "Rodriguez");
            clientData.put("secondSurname", "Ruiz");
            clientData.put("phone", "3003000000");
            clientData.put("address", "Calle sin fin");
            clientData.put("city", "Bogotá");

            return new ResponseEntity<>(clientData, HttpStatus.OK);
        } else {
            // Retorna un mensaje de error si el cliente no existe
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
    }
}