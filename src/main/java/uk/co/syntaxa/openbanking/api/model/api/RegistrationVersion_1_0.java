package uk.co.syntaxa.openbanking.api.model.api;


import uk.co.syntaxa.openbanking.api.model.exception.NotSupportedException;
import uk.co.syntaxa.openbanking.api.model.request.CreateRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.request.DeleteRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.request.GetRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.request.UpdateeRegistrationRequest;
import uk.co.syntaxa.openbanking.api.model.response.CreateRegistrationResponse;
import uk.co.syntaxa.openbanking.api.model.response.DeleteRegistrationResponse;
import uk.co.syntaxa.openbanking.api.model.response.GetRegistrationResponse;
import uk.co.syntaxa.openbanking.api.model.response.UpdateRegistrationResponse;

public interface RegistrationVersion_1_0 {

    CreateRegistrationResponse createRegistration(CreateRegistrationRequest request);
    GetRegistrationResponse getRegistration(GetRegistrationRequest request) throws NotSupportedException;
    UpdateRegistrationResponse updateRegistration(UpdateeRegistrationRequest request) throws NotSupportedException;
    DeleteRegistrationResponse deleteRegistration(DeleteRegistrationRequest request) throws NotSupportedException;
}
