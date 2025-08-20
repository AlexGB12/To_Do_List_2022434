package org.asco_devs.sistema_sareasp;

import org.asco_devs.sistema_sareasp.entity.Tareas;
import org.asco_devs.sistema_sareasp.repository.TareasRepository;
import org.asco_devs.sistema_sareasp.service.ITareaService;
import org.asco_devs.sistema_sareasp.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class SistemaTareaspApplication implements CommandLineRunner{

	//inyeccion de dependencias
	@Autowired
	private ITareaService tareaService;

	// Logger
	private static final Logger logger = LoggerFactory.getLogger(SistemaTareaspApplication.class);
	// Salto de línea
	String salto = System.lineSeparator();


	public static void main(String[] args) {
		SpringApplication.run(SistemaTareaspApplication.class, args);
		// antes de iniciar
		logger.info("Iniciando la aplicacion");
		SpringApplication.run(SistemaTareaspApplication.class, args);
		// al finalizar
		logger.info("Finalizando Aplicacion");
	}
	@Override
	public void run(String... args) throws Exception {
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
				logger.info(salto+"***Lista de Tareas***"+salto);
				List<Tareas> tareas = TareaService.listarTareas();
				tareas.forEach(Tareas -> logger.info(tareas.toString()+salto));
			}
			case 2 -> {
				logger.info(salto+"***Buscar Tarea por su codigo***"+salto);
				var codigo = Integer.parseInt(consola.nextLine());
				Tareas tareas = TareaService.buscarTareas(codigo);
				if (tareas != null) {
					logger.info("Cliente encontrado: " + tareas + salto);
				} else {
					logger.info("Cliente no encontrado" + tareas + salto);
				}
			}
			case 3 ->  {
				logger.info(salto+"***Agrega una tarea***"+salto);
				logger.info("Ingrese la descripcion de la tarea: ");
				var descripcion = consola.nextLine();
				logger.info("Ingrese la fecha  de la tarea: ");
				var fechaLimite = consola.nextLine();
				logger.info("Ingrese el estado de la tarea: ");
				var estado = consola.nextLine();
				var tareas = new Tareas();
				tareas.setNombre(nombre);
				tareas.setApellido(apellido);
				tareas.setTelefono(telefono);
				tareas.setCorreo(correo);
				tareas.setGenero(genero);
				tareas.setEdad(edad);
				tareaService.guardarTarea(tareas);
				logger.info("Tarea agregada: " + tareas + salto);
			}
			case 4 -> {
				logger.info(salto+"***Modificar cliente***"+salto);
				//buscar por codigo
				logger.info("Agregue el codigo del cliente a modificar");
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarCliente(codigo);
				//guardar si no es null
				if (cliente != null) {
					logger.info(salto+"***Agrega un cliente***"+salto);
					logger.info("Ingrese el nombre del cliente: ");
					var nombre = consola.nextLine();
					logger.info("Ingrese el apellido del cliente: ");
					var apellido = consola.nextLine();
					logger.info("Ingrese el telefono del cliente: ");
					var telefono = consola.nextLine();
					logger.info("Ingrese el correo del cliente: ");
					var correo = consola.nextLine();
					logger.info("Ingrese el genero del cliente: ");
					var genero = consola.nextLine();
					logger.info("Ingrese la edad del cliente: ");
					var edad = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setTelefono(telefono);
					cliente.setCorreo(correo);
					cliente.setGenero(genero);
					cliente.setEdad(edad);
					clienteService.guardarCliente(cliente);
					logger.info("Cliente modificado: " + cliente + salto);
				}else {
					logger.info("Cliente NO encontrado" + cliente + salto);
				}
			}
			case 5 -> {
				logger.info(salto+"***Eliminar Cliente***"+salto);
				logger.info("ingrese el codigo del cliente a eliminar");
				var codigo = Integer.parseInt(consola.nextLine());
				var cliente = clienteService.buscarCliente(codigo);
				if (cliente != null) {
					clienteService.eliminarCliente(cliente);
					logger.info("cliente Eliminado"+ cliente + salto);
				} else {
					logger.info("Cliente no encontrado"+cliente+salto);
				}
			}
			case 6 -> {
				logger.info("Bye"+salto+salto);
				salir = true;
			}
			default -> logger.info("Opción no válida");
		}
		return false;
	}
}
