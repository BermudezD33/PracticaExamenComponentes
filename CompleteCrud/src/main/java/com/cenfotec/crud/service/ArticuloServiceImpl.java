package com.cenfotec.crud.service;

import com.cenfotec.crud.domain.Article;
import com.cenfotec.crud.repo.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    ArticuloRepository repo;

    @Override
    public void save(Article articulo) {
        repo.save(articulo);
    }
}
