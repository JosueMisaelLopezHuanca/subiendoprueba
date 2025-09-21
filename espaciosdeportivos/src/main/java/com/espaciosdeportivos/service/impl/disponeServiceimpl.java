package com.espaciosdeportivos.service.impl;

import com.espaciosdeportivos.dto.disponeDTO;
import com.espaciosdeportivos.model.Cancha;
import com.espaciosdeportivos.model.Equipamiento;
import com.espaciosdeportivos.model.dispone;
import com.espaciosdeportivos.model.disponeId;
import com.espaciosdeportivos.repository.CanchaRepository;
import com.espaciosdeportivos.repository.EquipamientoRepository;
import com.espaciosdeportivos.repository.disponeRepository;
import com.espaciosdeportivos.service.IdisponeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/* 
@Service
public class disponeServiceimpl implements IdisponeService  {

}*/
