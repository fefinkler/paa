/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface IDAO {
    
    ArrayList<Object> objs = new ArrayList<>();
    Object obj = new Object();

    public boolean salvar(Object o);

    public boolean atualizar(Object o);

    public boolean excluir(Object o);

    public ArrayList<Object> consultarTodos();

    //public ArrayList<Object> consultar(String criterio);

    public Object consultarId(int id);
}
