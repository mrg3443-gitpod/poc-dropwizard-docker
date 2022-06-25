package com.gitpod;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/accounts")
public class AccountsResource {

    private final AccountDAO accountDAO;

    public AccountsResource(AccountDAO transactionDAO) {
        this.accountDAO = transactionDAO;
    }

    /**
     * Endpoint to get an account, or return 404 if it doesn't exist.
     */
    @Path("/{id}")
    @GET
    public Response getAccount(@PathParam("id") int id) {
        // Fetch the account from the database
        Optional<Account> account = accountDAO.getAccount(id);

        if (account.isPresent()) {
            AccountResponse response = new AccountResponse(account.get());
            return Response
                .ok()
                .entity(response)
                .build();
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    public static class AccountResponse {

        private final Account data;

        public AccountResponse(Account data) {
            this.data = data;
        }

        public Account getData() {
            return data;
        }
    }
}
