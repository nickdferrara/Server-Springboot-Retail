package com.nickdferrara.retailstore.accounting.repository

import com.nickdferrara.retailstore.accounting.domain.Accounting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountingRepository : JpaRepository<Accounting, Long>
