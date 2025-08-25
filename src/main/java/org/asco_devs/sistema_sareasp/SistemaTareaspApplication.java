package org.asco_devs.sistema_sareasp;

import org.asco_devs.sistema_sareasp.entity.Tareas;
import org.asco_devs.sistema_sareasp.service.ITareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SistemaTareaspApplication implements CommandLineRunner {

    @Autowired
    private ITareaService tareaService;

    private static final Logger logger = LoggerFactory.getLogger(SistemaTareaspApplication.class);
    String salto = System.lineSeparator();

    public static void main(String[] args) {
        logger.info("Iniciando la aplicacion");
        SpringApplication.run(SistemaTareaspApplication.class, args);
        logger.info("Finalizando Aplicacion");
    }

    @Override
    public void run(String... args) {
        SistemaTareaspApp();
    }

    private void SistemaTareaspApp() {
        logger.info("+++++ Bienvenido a la tareas pendientes +++++");
        var salir = false;
        var consola = new Scanner(System.in);

        while (!salir) {
            var opcion = mostrarMenu(consola);
            salir = ejecutarOpciones(consola, opcion);
            logger.info(salto);
        }
    }

    private int mostrarMenu(Scanner consola) {
        logger.info("""
                *** Aplicacion ***
                1. Listar Tarea
                2. Buscar Tarea
                3. Agregar Tarea
                4. Modificar Tarea
                5. Eliminar Tarea
                6. Salir
                """);
        logger.info("Selecciona una opcion: ");
        return consola.nextInt();
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion) {
        var salir = false;
        switch (opcion) {
            case 1 -> {
                logger.info(salto + "***Lista de Tareas***" + salto);
                List<Tareas> tareas = tareaService.listarTareas();
                tareas.forEach(tarea -> logger.info(tarea.toString() + salto));
            }
            case 2 -> {
                logger.info(salto + "***Buscar Tarea por su ID***" + salto);
                logger.info("Ingrese el ID de la tarea: ");
                var idTarea = consola.nextInt();
                consola.nextLine();
                Tareas tarea = tareaService.buscarTareas(idTarea);
                if (tarea != null) {
                    logger.info("Tarea encontrada: " + tarea + salto);
                } else {
                    logger.info("Tarea no encontrada." + salto);
                }
            }
            case 3 -> {
                logger.info(salto + "***Agrega una tarea***" + salto);
                consola.nextLine();
                logger.info("Ingrese la descripcion de la tarea: ");
                var descripcion = consola.nextLine();
                logger.info("Ingrese la fecha limite de la tarea (formato YYYY-MM-DD): ");
                var fechaStr = consola.nextLine();
                Date fechaLimite;
                try {
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    fechaLimite = formatoFecha.parse(fechaStr);
                } catch (ParseException e) {
                    logger.error("Error al convertir la fecha. La tarea no se guardará.");
                    return salir;
                }
                logger.info("Ingrese el estado de la tarea (e.g., activa/completada): ");
                var estado = consola.nextLine();
                var nuevaTarea = new Tareas();
                nuevaTarea.setDescripcion(descripcion);
                nuevaTarea.setFechaLimite(fechaLimite);
                nuevaTarea.setEstado(estado);
                tareaService.guardarTareas(nuevaTarea);
                logger.info("Tarea agregada: " + nuevaTarea + salto);
            }
            case 4 -> {
                logger.info(salto + "***Modificar Tarea***" + salto);
                logger.info("Ingrese el ID de la tarea a modificar: ");
                var idTarea = consola.nextInt();
                consola.nextLine();
                Tareas tareaExistente = tareaService.buscarTareas(idTarea);
                if (tareaExistente != null) {
                    logger.info("Tarea a modificar: " + tareaExistente + salto);
                    logger.info("Ingrese la nueva descripcion (dejar en blanco para no modificar): ");
                    var nuevaDescripcion = consola.nextLine();
                    if (!nuevaDescripcion.isBlank()) {
                        tareaExistente.setDescripcion(nuevaDescripcion);
                    }
                    logger.info("Ingrese el nuevo estado (dejar en blanco para no modificar): ");
                    var nuevoEstado = consola.nextLine();
                    if (!nuevoEstado.isBlank()) {
                        tareaExistente.setEstado(nuevoEstado);
                    }
                    tareaService.guardarTareas(tareaExistente);
                    logger.info("Tarea modificada: " + tareaExistente + salto);
                } else {
                    logger.info("Tarea no encontrada." + salto);
                }
            }
            case 5 -> {
                logger.info(salto + "***Eliminar Tarea***" + salto);
                logger.info("Ingrese el ID de la tarea a eliminar: ");
                var idTarea = consola.nextInt();
                consola.nextLine();
                Tareas tarea = tareaService.buscarTareas(idTarea);
                if (tarea != null) {
                    tareaService.eliminarTareas(tarea);
                    logger.info("Tarea eliminada: " + tarea + salto);
                } else {
                    logger.info("Tarea no encontrada." + salto);
                }
            }
            case 6 -> {
                logger.info("Bye" + salto + salto);
                salir = true;
            }
            default -> logger.info("Opción no válida");
        }
        return salir;
    }
}
