package uk.co.syntaxa.openbanking.api.model.api;


import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;
import uk.co.syntaxa.openbanking.api.model.request.CreateRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.request.DeleteRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.request.GetRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.request.UpdateRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.response.CreateRegistrationResponse;
import uk.co.syntaxa.openbanking.api.model.response.DeleteRegistrationResponse;
import uk.co.syntaxa.openbanking.api.model.response.GetRegistrationResponse;
import uk.co.syntaxa.openbanking.api.model.response.UpdateRegistrationResponse;
s
public interface RegistrationVersion_3_1 {

    CreateRegistrationResponse createRegistration(CreateRegistrationRequest request);
    GetRegistrationResponse getRegistration(GetRegistrationRequest request) throws NotSupportedException;
    UpdateRegistrationResponse updateRegistration(UpdateRegistrationRequest request) throws NotSupportedException;
    DeleteRegistrationResponse deleteRegistration(DeleteRegistrationRequest request) throws NotSupportedException;
}
