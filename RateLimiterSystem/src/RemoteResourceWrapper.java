class RemoteResourceWrapper implements RemoteResource {

    private RLService rlService;

    public RemoteResourceWrapper(RLService rlService) {
        this.rlService = rlService;
    }

    @Override
    public String callRemote(RequestDTO request) {
        if (!rlService.isAllowed(request)) {
            return "Rate Limit Exceeded";
        }

        return "Remote Resource Accessed";
    }
}