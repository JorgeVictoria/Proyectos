package com.jovian.ejemplodao

import com.jovian.ejemplodao.model.People

interface InterfaceDAO {

    fun load(): People
    fun save(): People
}