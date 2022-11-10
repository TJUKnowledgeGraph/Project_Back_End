package com.example.knowledgegraph.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "advanced search")
@RestController
@RequestMapping(value = "advanced")
public class AdvancedSearchController extends BaseController{
}
