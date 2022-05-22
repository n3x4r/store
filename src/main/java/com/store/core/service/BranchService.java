package com.store.core.service;

import com.store.core.domain.Branch;
import com.store.core.domain.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<Branch> findAll() {
        return StreamSupport.stream(branchRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Branch saveBranch(Branch newBranch) {
        return branchRepository.save(newBranch);
    }

    public Branch updateBranch(Integer id, Branch updateSourceBranch) {
        return branchRepository.save(updateSourceBranch);
    }

    public void deleteBranch(Integer id) {
        branchRepository.deleteById(id);
    }
}
