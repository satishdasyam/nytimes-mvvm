package com.satish.nytimesmvvm.repository;

import com.satish.nytimesmvvm.repository.local.NyDao;
import com.satish.nytimesmvvm.repository.remote.ApiHelper;

import javax.inject.Inject;

public class NyRepository {

    private final ApiHelper apiHelper;
    private final NyDao nyDao;

    @Inject
    public NyRepository(ApiHelper apiHelper, NyDao nyDao) {
        this.apiHelper = apiHelper;
        this.nyDao = nyDao;
    }
}
