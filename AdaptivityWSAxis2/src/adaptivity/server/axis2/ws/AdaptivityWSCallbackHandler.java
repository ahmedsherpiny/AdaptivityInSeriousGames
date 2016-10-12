
/**
 * AdaptivityWSCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package adaptivity.server.axis2.ws;

    /**
     *  AdaptivityWSCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class AdaptivityWSCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public AdaptivityWSCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public AdaptivityWSCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for addSkill method
            * override this method for handling normal response from addSkill operation
            */
           public void receiveResultaddSkill(
                    adaptivity.server.axis2.ws.AdaptivityWSStub.AddSkillResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addSkill operation
           */
            public void receiveErroraddSkill(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getGameModel method
            * override this method for handling normal response from getGameModel operation
            */
           public void receiveResultgetGameModel(
                    adaptivity.server.axis2.ws.AdaptivityWSStub.GetGameModelResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getGameModel operation
           */
            public void receiveErrorgetGameModel(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setGameModel method
            * override this method for handling normal response from setGameModel operation
            */
           public void receiveResultsetGameModel(
                    adaptivity.server.axis2.ws.AdaptivityWSStub.SetGameModelResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setGameModel operation
           */
            public void receiveErrorsetGameModel(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addPlayer method
            * override this method for handling normal response from addPlayer operation
            */
           public void receiveResultaddPlayer(
                    adaptivity.server.axis2.ws.AdaptivityWSStub.AddPlayerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addPlayer operation
           */
            public void receiveErroraddPlayer(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for isActive method
            * override this method for handling normal response from isActive operation
            */
           public void receiveResultisActive(
                    adaptivity.server.axis2.ws.AdaptivityWSStub.IsActiveResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isActive operation
           */
            public void receiveErrorisActive(java.lang.Exception e) {
            }
                


    }
    