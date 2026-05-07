package com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.presentacion;


/**
 *
 * @author Julian
 */
public class ControlForms {

//    private JFrame frameActual;
//    private ObjetosBO objetosBO;
//    private Membresia membresia;
//    private Cliente clienteActual;
//    private static Long contadorIdMembresia = 1L;
//
//    private final IMembresiaCompradaBO membresiaCompradaBo = new MembresiaCompradaBOMock(new MembresiaCompradaMock());
//
//    public ControlForms() {
//        objetosBO = new ObjetosBO();
//    }
//
//    private void mostrarPantalla(JFrame nuevoFrame) {
//        if (this.frameActual != null) {
//            this.frameActual.dispose();
//        }
//
//        this.frameActual = nuevoFrame;
//        this.frameActual.setResizable(false);
//        frameActual.setLocationRelativeTo(null);
//        frameActual.setVisible(true);
//    }
//
//    private void mostrarDialogo(JDialog nuevoDialogo) {
//        nuevoDialogo.setResizable(false);
//        nuevoDialogo.setLocationRelativeTo(this.frameActual);
//        nuevoDialogo.setVisible(true);
//    }
//
//    //frames
//    public void navegarMenuPrincipal() {
//        mostrarPantalla(new MainFitLifeFORM(this));
//    }
//
//    public void navegarBenificios() {
//        mostrarPantalla(new BeneficiosFORM(this));
//    }
//
//    public void navegarBienvenida() {
//        mostrarPantalla(new BienvenidaFORM(this));
//    }
//
//    public void navegarMetodosPago() {
//        mostrarPantalla(new SuscribirseFORM(this));
//    }
//
//    //Dialogs
//    public void navegarRegistrarse() {
//        mostrarDialogo(new RegistrarseFORM(this.frameActual, true, this));
//    }
//
//    public void navegarTransferenciaMetodo() {
//        mostrarDialogo(new TransferenciaFORM(this.frameActual, true));
//    }
//
//    public void navegarTarjetaMetodo() {
//        mostrarDialogo(new TarjetaFORM(this.frameActual, true, this));
//    }
//
//    //logica de botones }
//    public void registrarCliente(NuevoClienteDTO cliente) {
//        this.clienteActual = objetosBO.getClientesBO().registrarCliente(cliente);
//    }
//
//    //utileria
//    public void buscarClientePorId(Long id) {
//        objetosBO.getClientesBO().buscarClientePorId(id);
//    }
//
//    public List<Cliente> consultarClientes() {
//        return objetosBO.getClientesBO().consultarClientes();
//    }
//
//    public void seleccinarMembresia(String tipo) {
//        double precio;
//        TipoMembresia tipoMembresia;
//
//        switch (tipo) {
//            case "Oro":
//                tipoMembresia = TipoMembresia.ORO;
//                precio = 750.0;
//                break;
//            case "Plata":
//                tipoMembresia = TipoMembresia.PLATA;
//                precio = 500.0;
//                break;
//            default:
//                tipoMembresia = TipoMembresia.BRONCE;
//                precio = 300.0;
//        }
//        this.membresia = new Membresia(contadorIdMembresia++, tipoMembresia, precio, LocalDate.now().plusMonths(1));
//    }
//
//    public void procesarPagoTarjeta() {
//        LocalDate hoy = LocalDate.now();
//        MembresiaCompradaDTO compraDTO = new MembresiaCompradaDTO(membresia, hoy, hoy.plusMonths(1), membresia.getPrecio(), Estado.ACTIVO);
//
//        MembresiaComprada membresiaReal = membresiaCompradaBo.guardar(compraDTO);
//
//        if (this.clienteActual != null) {
//            this.clienteActual.setMembresíaComprada(membresiaReal);
//            System.out.println("Membresía asignada a " + clienteActual.getNombre());
//        }
//    }
//
//    public Membresia getMembresiaSeleccionada() {
//        return membresia;
//    }
//
//    public MembresiaComprada getMembresiaComprada() {
//        return membresiaCompradaBo.obtenerTodas().get(membresiaCompradaBo.obtenerTodas().size() - 1);
//    }
//
//    public Cliente getClienteActual() {
//        return clienteActual;
//    }

}
