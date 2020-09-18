//package com.windmill.zyfz.syncTimer.unit;
//
//public class WorkerHandler implements Runnable {  
//        ResultSet coreRs;  
//        String queryStr;   
//        String targetTBName;  
//        String values;  
//
//        //String values = "VALUES (?,?,?,?,?)"
//        public WorkerHandler(String queryStr,String targetTBName,String values) {  
//            this.queryStr = queryStr;   
//            this.targetTBName = targetTBName;  
//            this.values = values;  
//
//        }  
//        @Override  
//        public void run() {   
//                // 开始同步
//              launchSyncData();              
//        }  
//        // 同步数据方法
//        void launchSyncData()  {  
//            // 获得核心库连接
//            Connection coreConnection = fromconn.getFromConn();
//            Statement coreStmt = null;
//            ResultSet coreRs = null;
//           // 获得目标库连接
//            Connection targetConn = toconn.getToConn();
//            PreparedStatement targetPstmt = null;
//			try {
//				coreStmt = coreConnection.createStatement();
//				
//	            targetConn.setAutoCommit(false);// 设置手动提交,取消
//	            targetPstmt = targetConn.prepareStatement("INSERT INTO " + targetTBName + values);  
//	            coreRs = coreStmt.executeQuery(queryStr);  
//	            log.info(Thread.currentThread().getName()+"'s Query SQL::"+queryStr);  
//	            int batchCounter = 0; // 累加的批处理数量
//	            int whs = values.substring(values.indexOf("(")+1,values.length()-1).trim().split(",").length;
//	            while (coreRs.next()) {  
//	                for(int i = 0;i<whs;i++){
//	                	targetPstmt.setString(i+1, coreRs.getString(i+2));
//	                }
//	                //targetPstmt.execute();
//	                targetPstmt.addBatch();  
//	                batchCounter++;  
//	                currentSynCount.incrementAndGet();// 递增
//	                if (batchCounter % 10000 == 0) { // 1万条数据一提交
//	                    targetPstmt.executeBatch();  
//	                    targetPstmt.clearBatch();  
//	                    targetConn.commit();  
//	                } 
//	            }  
//	            // 提交剩余的批处理
//	            targetPstmt.executeBatch();  
//	            targetPstmt.clearBatch(); 
//	            targetConn.commit();  
//	            // 释放连接
//			} catch (SQLException e) {
//				e.printStackTrace();
//				res= "failed" ;
//			}  finally{
//				try {
//					if(targetPstmt!=null)
//					targetPstmt.close();
//					if(coreRs!=null)
//					coreRs.close();
//					if(coreStmt!=null)
//					coreStmt.close();
//					if(targetConn!=null)
//					targetConn.close();
//					if(coreConnection!=null)
//					coreConnection.close();
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}	
//            res = "success";
//        }  
//}
