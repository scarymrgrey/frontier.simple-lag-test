/*******************************************************************************
 * Copyright (c) 2014 Imperial College London
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Raul Castro Fernandez - initial API and implementation
 ******************************************************************************/
package operators;

import java.util.List;
import java.util.Map;

import uk.ac.imperial.lsds.seep.comm.serialization.DataTuple;
import uk.ac.imperial.lsds.seep.comm.serialization.messages.TuplePayload;
import uk.ac.imperial.lsds.seep.operator.StatelessOperator;


public class Source implements StatelessOperator {

	private static final long serialVersionUID = 1L;
	private static final int workerUUID = (int)(System.currentTimeMillis() - Constants.timeOffset);

	public void setUp() {
		
	}
	
	public void processData(DataTuple dt) {
		Map<String, Integer> mapper = api.getDataMapper();
		DataTuple data = new DataTuple(mapper, new TuplePayload());
		
		while(true){
			int currTimestampOffset = (int)(System.currentTimeMillis() - Constants.timeOffset);
			int payload = 5;
			
			DataTuple output = data.newTuple(currTimestampOffset, workerUUID, payload);
			
			api.send(output);
			
			try {
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void processData(List<DataTuple> arg0) {
		// TODO Auto-generated method stub
		
	}
}
