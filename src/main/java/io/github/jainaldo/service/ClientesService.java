package io.github.jainaldo.service;

import io.github.jainaldo.model.Cliente;
import io.github.jainaldo.repository.ClientesTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    public ClientesTestRepository repository;

    public ClientesService( ClientesTestRepository repository) {
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente) {
        this.validarCliente(cliente);
        repository.persitir(cliente);

    }

    public void validarCliente(Cliente cliente) {
        ///valida cliente
    }
}
