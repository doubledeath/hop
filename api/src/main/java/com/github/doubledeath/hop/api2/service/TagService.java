package com.github.doubledeath.hop.api2.service;

import com.github.doubledeath.hop.api2.model.ComplexTag;
import com.github.doubledeath.hop.api2.model.SimpleTag;

/**
 * Created by doubledeath on 2/20/17.
 */
public interface TagService {

    SimpleTag createSimpleTag();

    ComplexTag createComplexTag(String target);

}
