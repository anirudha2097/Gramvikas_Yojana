package com.masai.usecases;

import java.util.List;

import com.masai.dao.BlockDevelopmentOfficerDao;
import com.masai.dao.BlockDevelopmentOfficerDaoImpl;
import com.masai.demo.Menu;
import com.masai.exceptions.GramPanchayatMemberException;
import com.masai.model.GramPanchayatMember;

public class GetAllDetailsOfGPM {

	public static void main(String[] args){
		
		try {
			BlockDevelopmentOfficerDao bdo = new BlockDevelopmentOfficerDaoImpl();
			List<GramPanchayatMember> gpmlist = bdo.getAllGpm();
			System.out.println("");
			gpmlist.forEach(gpm -> {System.out.println("Name: "+gpm.getName()+", "+"GPM ID: "+gpm.getGpmid()+", "+"Address: "+gpm.getAddress()+", "+"Email: "+gpm.getEmail());
			System.out.println("");
			});
			
		} catch (GramPanchayatMemberException e) {
			System.out.println(e.getMessage());
		}

		finally {
			System.out.println("---------------------------------------------------------------");
			System.out.println("");
			Menu.BDOMenu();
		}
		
	}

}
