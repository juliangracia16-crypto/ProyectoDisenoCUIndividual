
package com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.presentacion;

import com.mycompany.fitlifegym_negocio.IClientesBO;
import com.mycompany.fitlifegym_negocio.fabricaBO.FabricaBO;
import com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.FuncionalidadRegistroUsuario;
import com.mycompany.funcionalidadcomprarmembresiausuarionoregistrado.IFuncionalidadRegistrarUsuario;

/**
 *
 * @author Julian
 */
public class FabricaSubsistemaRegistroUsuario {
    public static IFuncionalidadRegistrarUsuario crearSubsistemaRegistroUsuario(){
        IClientesBO clientesBO = FabricaBO.crearClientesBO();
        return new FuncionalidadRegistroUsuario(clientesBO);
    }
}
