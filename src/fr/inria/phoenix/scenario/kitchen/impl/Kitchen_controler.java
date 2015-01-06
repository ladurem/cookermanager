package fr.inria.phoenix.scenario.kitchen.impl;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.coockerstate.*;
import fr.inria.phoenix.diasuite.framework.context.kitchencontext.*;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.KitchenStatusValue;
import fr.inria.phoenix.diasuite.framework.controller.kitchencontroller.AbstractKitchenController;
import fr.inria.phoenix.diasuite.framework.datatype.coockerstate.*;
import fr.inria.phoenix.diasuite.framework.datatype.doorstate.*;

public class Kitchen_controler extends AbstractKitchenController {

	public Kitchen_controler(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	@Override
	protected void onKitchenStatus(KitchenStatusValue kitchenStatus, DiscoverForKitchenStatus discover) {
		
		
		
		DiaLog.info("Lancement du controller");
		KitchenStatusValue status = kitchenStatus;
		
		//TODO a finir
		if(status.equals("WARN")){
			
		}else if(status.equals("STOP")){
			
		}else if(status.equals("OK")){
			
		}else{
			DiaLog.warning("Erreur critique");
			System.out.println("Erreur critique");
			return;
		}
		

	}
}
