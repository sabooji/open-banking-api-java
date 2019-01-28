Each Provider (Bank) will have it's own class.

That class will implement an interface covering the individual endpoints for that type of API

Each API endpoint method will return a *Response object. These will take in a *Request object containing all input parameters.

The *Response object has sync/async methods which then execute the request
