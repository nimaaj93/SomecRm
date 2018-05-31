<template>
  <div class="content-wrapper">
    <section class="content-header">
      <div class="row">
        <span class="col-md-2">
        </span>
        <span class="col-md-8">
          <h2>
            <i class="fa fa-list" aria-hidden="true"></i>
            {{ 'sidebar.menu.order.management.list' | message }}
          </h2>
        </span>
      </div>
    </section>
    <section class="content">
      <div class="row">
        <div class="col-md-1">
        </div>
        <div class="col-md-10">
          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <h3 class="box-title">
                    {{'common.search' | message}}
                    <i class="fa fa-search" aria-hidden="true"></i>
                  </h3>
                </div>
                <div class="box-body row" :class="{'form-submit' : submitting}">
                  <div class="col-md-1">
                  </div>
                  <div class="col-md-10">
                    <form v-on:submit.prevent="searchOrders" role="form" novalidate>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label for="searchPhoneNumber" class="sr-only">{{'common.phoneNumber' | message}}</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                  <i class="fa fa-phone-square" aria-hidden="true"></i>
                                </span>
                              <input type="text" id="searchPhoneNumber" class="form-control"
                                     v-focus
                                     autocomplete="off"
                                     maxlength="11"
                                     :disabled="submitting"
                                     name="searchPhoneNumber"
                                     :placeholder="'common.phoneNumber' | message"
                                     v-model="searchParams.props.phoneNumber">
                            </div>
                          </div>
                        </div>

                        <div class="col-md-6">
                          <div class="form-group">
                            <label for="searchNationalCode" class="sr-only">{{'common.nationalCode' | message}}</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                  <i class="fa fa-phone-square" aria-hidden="true"></i>
                                </span>
                              <input type="text" id="searchNationalCode" class="form-control"
                                     autocomplete="off"
                                     maxlength="10"
                                     :disabled="submitting"
                                     name="searchNationalCode"
                                     :placeholder="'common.nationalCode' | message"
                                     v-model="searchParams.props.nationalCode">
                            </div>
                          </div>
                        </div>

                      </div>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group">
                            <label for="searchFromDate" class="sr-only">{{'common.fromDate' | message}}</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                  <i class="fa fa-phone-square" aria-hidden="true"></i>
                                </span>
                              <input type="text" id="searchFromDate" class="form-control"
                                     v-mask="'####/##/##'"
                                     autocomplete="off"
                                     maxlength="10"
                                     :disabled="submitting"
                                     name="searchFromDate"
                                     :placeholder="'common.fromDate' | message"
                                     v-model="searchParams.props.fromDate">
                            </div>
                          </div>
                        </div>

                        <div class="col-md-6">
                          <div class="form-group">
                            <label for="searchToDate" class="sr-only">{{'common.toDate' | message}}</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                  <i class="fa fa-phone-square" aria-hidden="true"></i>
                                </span>
                              <input type="text" id="searchToDate" class="form-control"
                                     v-mask="'####/##/##'"
                                     autocomplete="off"
                                     maxlength="10"
                                     :disabled="submitting"
                                     name="searchToDate"
                                     :placeholder="'common.toDate' | message"
                                     v-model="searchParams.props.toDate">
                            </div>
                          </div>
                        </div>

                      </div>

                      <div class="row">
                        <div class="col-md-6">
                          <button class="btn btn-primary"
                                  :disabled="submitting"
                                  type="submit">
                            {{'common.search' | message}}
                          </button>
                        </div>
                      </div>
                    </form>
                  </div>
                  <div class="col-md-1">
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <lazy :loaded="loaded">
              <div class="col-xs-12">
                <div class="box">
                  <div class="box-body">

                    <ul class="nav nav-tabs nav-justified">
                      <li :class="{active : searchParams.props.orderStatus === 400}"><a href="#" @click="setActiveTab(400)">{{'order.status.new' | message}}</a></li>
                      <li :class="{active : searchParams.props.orderStatus === 401}"><a href="#" @click="setActiveTab(401)">{{'order.status.inprogress' | message}}</a></li>
                      <li :class="{active : searchParams.props.orderStatus === 402}"><a href="#" @click="setActiveTab(402)">{{'order.status.waiting.for.payment' | message}}</a></li>
                      <li :class="{active : searchParams.props.orderStatus === 403}"><a href="#" @click="setActiveTab(403)">{{'order.status.paid' | message}}</a></li>
                      <li :class="{active : searchParams.props.orderStatus === 404}"><a href="#" @click="setActiveTab(404)">{{'order.status.cancel' | message}}</a></li>
                    </ul>

                    <div class="table-wrapper" v-if="orders.length > 0">
                      <ul class="pagination">
                        <li v-for="n in pages" :class="{active : searchParams.page == n}">
                          <a @click="setPage(n)">{{n}}</a>
                        </li>
                      </ul>
                      <table id="usersDataTable" class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                          <th>{{'common.row' | message}}</th>
                          <th>{{'common.fullName' | message}}</th>
                          <th>{{'common.fullName.en' | message}}</th>
                          <th>{{'common.phoneNumber' | message}}</th>
                          <th>{{'order.submit.date' | message}}</th>
                          <th>{{'order.number' | message}}</th>
                          <th>{{'order.category' | message}}</th>
                          <th>{{'common.status' | message}}</th>
                          <th>{{'common.description' | message}}</th>
                          <th>{{'common.actions' | message}}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(order,index) in orders">
                          <td>{{((searchParams.page - 1) * 10) + index + 1}}</td>
                          <td>{{order.providerOrderFirstNameFa}} {{order.providerOrderLastNameFa}}</td>
                          <td>{{order.providerOrderFirstNameEn}} {{order.providerOrderLastNameEn}}</td>
                          <td>{{order.phoneNumber}}</td>
                          <td>{{order.orderDate}}</td>
                          <td>{{order.orderNumber}}</td>
                          <td>
                            <span v-for="(category, index) in order.categoryDtoList.slice().reverse()">
                               {{category.titleFa}} <i v-if="index != (order.categoryDtoList.length - 1)" class="fa fa-chevron-left" aria-hidden="true"></i>
                            </span>
                          </td>
                          <td>
                            <span v-if="order.orderStatus === 400">
                                {{'order.status.new' | message}}
                            </span>
                            <span v-if="order.orderStatus === 401">
                                {{'order.status.inprogress' | message}}
                            </span>
                            <span v-if="order.orderStatus === 402">
                                {{'order.status.waiting.for.payment' | message}}
                            </span>
                            <span v-if="order.orderStatus === 403">
                                {{'order.status.paid' | message}}
                            </span>
                            <span v-if="order.orderStatus === 404">
                                {{'order.status.cancel' | message}}
                            </span>
                          </td>
                          <td>{{order.description | summarize(40)}}</td>
                          <td>
                            <div class="input-group">
                              <div class="input-group-btn">
                                <a class="btn dropdown-toggle" data-toggle="dropdown">
                                  <i class="fa fa-cog" aria-hidden="true"></i>
                                </a>
                                <ul class="dropdown-menu">
                                  <li>
                                    <a @click="setSelectedOrder(order)" data-toggle="modal" data-target="#orderViewPopup">
                                      <i class="fa fa-eye" aria-hidden="true"></i>
                                      {{'common.view.detail' | message}}
                                    </a>
                                  </li>
                                  <li v-if="order.orderStatus === 400 | order.orderStatus === 401 | order.orderStatus === 402">
                                    <a @click="setSelectedOrder(order)" data-toggle="modal" data-target="#orderProcessPopup">
                                      <i class="fa fa-wrench" aria-hidden="true"></i>
                                      {{'order.change.status' | message}}
                                    </a>
                                  </li>
                                  <!--<li v-if="order.orderStatus === 402">-->
                                    <!--<a @click="payOrder(order)">-->
                                      <!--<i class="fa fa-money" aria-hidden="true"></i>-->
                                      <!--{{'order.pay' | message}}-->
                                    <!--</a>-->
                                  <!--</li>-->
                                  <li v-if="order.orderStatus === 400 | order.orderStatus === 401 | order.orderStatus === 402">
                                    <a @click="cancelOrder(order)">
                                      <i class="fa fa-window-close" aria-hidden="true"></i>
                                      {{'order.cancel' | message}}
                                    </a>
                                  </li>
                                  <li>
                                    <a data-toggle="modal" data-target="#orderLogsPopup" @click="loadLogs(order)">
                                      <i class="fa fa-archive"></i>
                                      {{'order.logs' | message}}
                                    </a>
                                  </li>
                                </ul>
                              </div>
                            </div>
                          </td>
                        </tr>
                        </tbody>
                      </table>
                      <ul class="pagination">
                        <li :class="{'disabled' : searchParams.page < 2}">
                          <a v-on="searchParams.page > 1 ? { mouseover: setPage(1) } : {}"><i class="fa fa-fast-forward"></i></a>
                        </li>
                        <li :class="{'disabled' : searchParams.page < 2}">
                          <a v-on="searchParams.page > 1 ? { mouseover: setPage(searchParams.page - 1) } : {}"><i class="fa fa-step-forward"></i></a>
                        </li>
                        <li v-for="n in pages"
                            v-if="Math.abs(n - searchParams.page) < 4"
                            :class="{active : searchParams.page == n}">
                          <a @click="setPage(n)">{{n}}</a>
                        </li>
                        <li :class="{'disabled' : searchParams.page > (pages - 2)}">
                          <a v-on="searchParams.page < pages ? { mouseover: setPage(searchParams.page + 1) } : {}"><i class="fa fa-step-backward"></i></a>
                        </li>
                        <li :class="{'disabled' : searchParams.page > (pages - 2)}">
                          <a v-on="searchParams.page < pages ? { mouseover: setPage(pages) } : {}"><i class="fa fa-fast-backward"></i></a>
                        </li>
                      </ul>
                    </div>
                    <div v-else>
                      {{'table.no.items.found' | message}}
                    </div>
                  </div>
                </div>
              </div>
            </lazy>
          </div>
        </div>
        <div class="col-md-1">
        </div>
      </div>
    </section>

    <!-- order process pop up -->
    <div id="orderProcessPopup" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <lazy :loaded="orderEditLoaded">
          <div class="modal-content" v-if="selectedOrder != null">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">{{'common.edit' | message}}</h4>
            </div>
            <div class="modal-body">
              <div class="row">
                <div class="f1">
                  <div class="f1-steps">
                    <div class="f1-progress">
                      <div class="f1-progress-line"
                           :data-now-value="progressPercent"
                           data-number-of-steps="4"
                           :style="{ width: progressPercent + '%' }"></div>
                    </div>
                    <div class="f1-step" :class="{'active' : selectedOrder.orderStatus >= 400}">
                      <div class="f1-step-icon"><i class="fa fa-user"></i></div>
                      <p>{{'order.status.new' | message}}</p>
                    </div>
                    <div class="f1-step" :class="{'active' : selectedOrder.orderStatus >= 401}">
                      <div class="f1-step-icon"><i class="fa fa-key"></i></div>
                      <p>{{'order.status.inprogress' | message}}</p>
                    </div>
                    <div class="f1-step" :class="{'active' : selectedOrder.orderStatus >= 402}">
                      <div class="f1-step-icon"><i class="fa fa-key"></i></div>
                      <p>{{'order.status.waiting.for.payment' | message}}</p>
                    </div>
                    <div class="f1-step" :class="{'active' : selectedOrder.orderStatus === 403}">
                      <div class="f1-step-icon"><i class="fa fa-twitter"></i></div>
                      <p>{{'order.status.paid' | message}}</p>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <strong>
                    {{'common.fullName' | message}}
                  </strong>
                  <p class="text-muted">
                    {{selectedOrder.providerOrderFirstNameFa}} {{selectedOrder.providerOrderLastNameFa}}
                  </p>
                </div>
                <div class="col-md-6">
                  <strong>
                    {{'common.fullName.en' | message}}
                  </strong>
                  <p class="text-muted">
                    {{selectedOrder.providerOrderFirstNameEn}} {{selectedOrder.providerOrderLastNameEn}}
                  </p>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'common.phoneNumber' | message}}
                  </strong>
                  <p class="text-muted">
                    {{selectedOrder.phoneNumber}}
                  </p>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'order.submit.date' | message}}
                  </strong>
                  <p class="text-muted">
                    {{selectedOrder.orderDate}}
                  </p>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'order.number' | message}}
                  </strong>
                  <p class="text-muted">
                    {{selectedOrder.orderNumber}}
                  </p>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'order.category' | message}}
                  </strong>
                  <div class="text-muted">
                    <span v-for="(category, index) in selectedOrder.categoryDtoList.slice().reverse()">
                               {{category.titleFa}} <i v-if="index != (selectedOrder.categoryDtoList.length - 1)" class="fa fa-chevron-left" aria-hidden="true"></i>
                    </span>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'common.status' | message}}
                  </strong>
                  <div class="text-muted">
                    <span v-if="selectedOrder.orderStatus === 400">
                                {{'order.status.new' | message}}
                    </span>
                    <span v-if="selectedOrder.orderStatus === 401">
                                {{'order.status.inprogress' | message}}
                    </span>
                    <span v-if="selectedOrder.orderStatus === 402">
                                {{'order.status.waiting.for.payment' | message}}
                    </span>
                    <span v-if="selectedOrder.orderStatus === 403">
                                {{'order.status.paid' | message}}
                    </span>
                    <span v-if="selectedOrder.orderStatus === 404">
                                {{'order.status.cancel' | message}}
                    </span>
                  </div>
                </div>
              </div>

              <!-- inputs -->
              <div class="row" v-if="selectedOrder.orderStatus === 400 | selectedOrder.orderStatus === 401 | selectedOrder.orderStatus === 402">
                <div class="col-md-10">
                  <div class="form-group" :class="{'has-error': errors.has('inputDescription')}">
                    <label class="control-label"
                           v-show="errors.has('inputDescription')">{{'common.required' | error}}</label>
                    <label for="inputDescription"
                           v-show="!errors.has('inputDescription')">{{'common.description' | message}} *</label>
                    <div class="input-group">
                      <textarea type="text" id="inputDescription" class="form-control container"
                                maxlength="1000"
                                :disabled="orderEditSubmitting"
                                name="inputDescription"
                                v-validate="'required'"
                                :placeholder="'common.description' | message"
                                v-model="selectedOrder.description">
                      </textarea>
                    </div>
                  </div>
                </div>
              </div>

              <!--<div class="row" v-if="selectedOrder.orderStatus === 401">-->
                <!--<div class="col-md-10">-->
                  <!--<div class="form-group" :class="{'has-error': errors.has('inputAmount')}">-->
                    <!--<label class="control-label"-->
                           <!--v-show="errors.has('inputAmount')">{{'common.required' | error}}</label>-->
                    <!--<label for="inputAmount"-->
                           <!--v-show="!errors.has('inputAmount')">{{'common.amount' | message}} *</label>-->
                    <!--<div class="input-group">-->
                      <!--<input type="text" id="inputAmount" class="form-control container"-->
                                <!--maxlength="20"-->
                                <!--:disabled="orderEditSubmitting"-->
                                <!--name="inputAmount"-->
                                <!--v-validate="'required'"-->
                                <!--:placeholder="'common.amount' | message"-->
                                <!--v-model="selectedOrder.amount">-->
                    <!--</div>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</div>-->
              <!-- inputs end -->

            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-primary float" @click="processOrder">{{'common.update' | message}}</button>
              <button type="button" class="btn btn-danger float" data-dismiss="modal">{{'common.cancel' | message}}</button>
            </div>
          </div>
        </lazy>
      </div>
    </div>
    <!-- order process pop up end -->

    <!-- order view popup -->
    <div id="orderViewPopup" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <lazy :loaded="true">
          <div class="modal-content" v-if="selectedOrder != null">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">{{'common.view.detail' | message}}</h4>
            </div>
            <div class="modal-body">
              <div class="row" v-if="selectedOrder.orderStatus !== 404">
                <div class="f1">
                  <div class="f1-steps">
                    <div class="f1-progress">
                      <div class="f1-progress-line"
                           :data-now-value="progressPercent"
                           data-number-of-steps="4"
                           :style="{ width: progressPercent + '%' }"></div>
                    </div>
                    <div class="f1-step" :class="{'active' : selectedOrder.orderStatus >= 400}">
                      <div class="f1-step-icon"><i class="fa fa-user"></i></div>
                      <p>{{'order.status.new' | message}}</p>
                    </div>
                    <div class="f1-step" :class="{'active' : selectedOrder.orderStatus >= 401}">
                      <div class="f1-step-icon"><i class="fa fa-key"></i></div>
                      <p>{{'order.status.inprogress' | message}}</p>
                    </div>
                    <div class="f1-step" :class="{'active' : selectedOrder.orderStatus >= 402}">
                      <div class="f1-step-icon"><i class="fa fa-key"></i></div>
                      <p>{{'order.status.waiting.for.payment' | message}}</p>
                    </div>
                    <div class="f1-step" :class="{'active' : selectedOrder.orderStatus === 403}">
                      <div class="f1-step-icon"><i class="fa fa-twitter"></i></div>
                      <p>{{'order.status.paid' | message}}</p>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6">
                  <strong>
                    {{'common.fullName' | message}}
                  </strong>
                  <p class="text-muted">
                    {{selectedOrder.providerOrderFirstNameFa}} {{selectedOrder.providerOrderLastNameFa}}
                  </p>
                </div>
                <div class="col-md-6">
                  <strong>
                    {{'common.fullName.en' | message}}
                  </strong>
                  <p class="text-muted">
                    {{selectedOrder.providerOrderFirstNameEn}} {{selectedOrder.providerOrderLastNameEn}}
                  </p>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'common.phoneNumber' | message}}
                  </strong>
                  <p class="text-muted">
                    {{selectedOrder.phoneNumber}}
                  </p>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'order.submit.date' | message}}
                  </strong>
                  <p class="text-muted">
                    {{selectedOrder.orderDate}}
                  </p>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'order.number' | message}}
                  </strong>
                  <p class="text-muted">
                    {{selectedOrder.orderNumber}}
                  </p>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'order.category' | message}}
                  </strong>
                  <div class="text-muted">
                    <span v-for="(category, index) in selectedOrder.categoryDtoList.slice().reverse()">
                               {{category.titleFa}} <i v-if="index != (selectedOrder.categoryDtoList.length - 1)" class="fa fa-chevron-left" aria-hidden="true"></i>
                    </span>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'common.status' | message}}
                  </strong>
                  <div class="text-muted">
                    <span v-if="selectedOrder.orderStatus === 400">
                                {{'order.status.new' | message}}
                    </span>
                    <span v-if="selectedOrder.orderStatus === 401">
                                {{'order.status.inprogress' | message}}
                    </span>
                    <span v-if="selectedOrder.orderStatus === 402">
                                {{'order.status.waiting.for.payment' | message}}
                    </span>
                    <span v-if="selectedOrder.orderStatus === 403">
                                {{'order.status.paid' | message}}
                    </span>
                    <span v-if="selectedOrder.orderStatus === 404">
                                {{'order.status.cancel' | message}}
                    </span>
                  </div>
                </div>
              </div>

              <div class="row" v-if="selectedOrder.amount != null & selectedOrder.amount != ''">
                <div class="col-md-12">
                  <strong>
                    {{'common.amount' | message}}
                  </strong>
                  <div class="text-muted">
                    <span>
                      {{selectedOrder.amount | amount}}
                    </span>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-12">
                  <strong>
                    {{'common.description' | message}}
                  </strong>
                  <div class="text-muted">
                    <span>
                      {{selectedOrder.description}}
                    </span>
                  </div>
                </div>
              </div>

              <div class="row" v-if="selectedOrder.uploadedFileName">
                <div class="col-md-12">
                  <strong>
                    {{'order.attachment' | message}}
                  </strong>
                  <div class="text-muted">
                    <a @click="downloadFile(selectedOrder)" class="attachment-link">
                      {{selectedOrder.uploadedFileName}}
                    </a>
                  </div>
                </div>
              </div>


            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-danger float" data-dismiss="modal">{{'common.close' | message}}</button>
            </div>
          </div>
        </lazy>
      </div>
    </div>
    <!-- order view popup end -->

    <!-- order logs popup start -->
    <div id="orderLogsPopup" class="modal fade" role="dialog">
      <div class="modal-dialog">
        <lazy :loaded="logLoaded">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" @click="logsClosed" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">{{'common.description' | message}}</h4>
            </div>
            <div class="modal-body">
              <div v-if="logItems && logItems.length > 0" class="logs-container">
                <div class="panel panel-success"
                      v-for="log in logItems">
                  <div class="panel-heading">{{log.logTitle}} <span class="pull-left">{{log.writerFirstNameFa + ' ' + log.writerLastNameFa}}</span></div>
                  <div class="panel-body">{{log.logContent}}</div>
                </div>
              </div>
              <div v-else style="margin: 10px">
                {{'table.no.items.found' | message}}
              </div>
              <div class="new-log" v-if="newLogDisplayed === true">
              <form>
                <div class="row">
                    <div class="col-md-10">
                      <div class="form-group" :class="{'has-error': errors.has('logTitle')}">
                        <label class="control-label"
                               v-show="errors.has('logTitle')">{{'common.required' | error}}</label>
                        <label for="logTitle"
                               v-show="!errors.has('logTitle')">{{'common.title' | message}} *</label>
                        <div class="input-group">
                          <input type="text" id="logTitle" class="form-control container"
                                 maxlength="50"
                                 :disabled="logSubmitting"
                                 name="logTitle"
                                 v-validate="'required'"
                                 :placeholder="'common.title' | message"
                                 v-model="newLog.logTitle">
                        </div>
                      </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-10">
                      <div class="form-group" :class="{'has-error': errors.has('orderLog')}">
                        <label class="control-label"
                               v-show="errors.has('orderLog')">{{'common.required' | error}}</label>
                        <label for="orderLog"
                               v-show="!errors.has('orderLog')">{{'common.description' | message}} *</label>
                        <div class="input-group">
                          <input type="text" id="orderLog" class="form-control container"
                                 maxlength="1000"
                                 :disabled="logSubmitting"
                                 name="orderLog"
                                 v-validate="'required'"
                                 :placeholder="'common.description' | message"
                                 v-model="newLog.logContent">
                        </div>
                      </div>
                    </div>
                </div>

                <button type="button" class="btn btn-primary float" @click="addLog()">{{'common.submit' | message}}</button>

              </form>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-danger float" @click="logsClosed" data-dismiss="modal">{{'common.close' | message}}</button>
            </div>
          </div>
        </lazy>
      </div>
    </div>
    <!-- order logs popup end -->

  </div>
</template>

<script>
  import axios from 'axios';
  import Lazy from '../../Lazy';

  export default {
    name: 'OrderManagement',
    components: {
      Lazy
    },
    data () {
      return {
        submitting: false,
        loaded: true,
        pages: 1,
        searchParams: {
          props: {
            orderStatus: 400
          },
          size: 10,
          page: 1
        },
        orders: [],
        selectedOrder: null,
        orderEditLoaded: true,
        orderEditSubmitting: false,
        logItems: [],
        newLog: {},
        logLoaded: true,
        logSubmitting: false,
        newLogDisplayed: false
      }
    },
    computed: {
      progressPercent: function () {
        if (!this.selectedOrder.orderStatus) {
          return 0
        }
        if (this.selectedOrder.orderStatus === 400) {
          return 12.5 * 1
        }
        if (this.selectedOrder.orderStatus === 401) {
          return 12.5 * 3
        }
        if (this.selectedOrder.orderStatus === 402) {
          return 12.5 * 5
        }
        if (this.selectedOrder.orderStatus === 403) {
          return 12.5 * 7
        }
      }
    },
    created: function () {
      this.loadOrders()
    },
    methods: {
      searchOrders: function() {
        this.searchParams.page = 1
        this.loadOrders()
      },
      setActiveTab(status) {
        this.searchParams.props.orderStatus = status
        this.searchParams.page = 1
        this.loadOrders()
      },
      loadOrders: function () {
          var countUrl = '/s/userorder/count'
          var listUrl = '/s/userorder/list'

        this.loaded = false
        this.submitting = true

        axios.get(countUrl, {
          params: {
            phoneNumber: this.searchParams.props.phoneNumber,
            nationalCode: this.searchParams.props.nationalCode,
            fromDate: this.searchParams.props.fromDate,
            toDate: this.searchParams.props.toDate,
            orderStatus: this.searchParams.props.orderStatus
          }
        })
          .then(response => {
            this.pages = parseInt(Math.ceil(response.data / this.searchParams.size))
          })
          .catch(e => {
          })

        axios.get(listUrl, {
          params: {
            phoneNumber: this.searchParams.props.phoneNumber,
            nationalCode: this.searchParams.props.nationalCode,
            fromDate: this.searchParams.props.fromDate,
            toDate: this.searchParams.props.toDate,
            orderStatus: this.searchParams.props.orderStatus,
            size: this.searchParams.size,
            page: this.searchParams.page - 1
          }
        })
          .then(response => {
            this.orders = response.data
            this.loaded = true
            this.submitting = false
          })
          .catch(e => {
            this.loaded = true
            this.submitting = false
          })
      },
      nextPage: function () {
        this.searchParams.page += 1
        this.loadOrders()
      },
      prevPage: function () {
        this.searchParams.page -= 1
        this.loadOrders()
      },
      setPage: function (pageNumber) {
        this.searchParams.page = pageNumber
        this.loadOrders()
      },
      setSelectedOrder: function (order) {
        this.selectedOrder = JSON.parse(JSON.stringify(order))
      },
      processOrder: function () {
        var updateUrl = '/s/userorder/update'

        this.$validator.validateAll().then((result) => {
          if (result) {
            this.orderEditSubmitting = true
            axios.post(updateUrl, {
              orderId: this.selectedOrder.orderId,
              description: this.selectedOrder.description,
              amount: this.selectedOrder.amount,
              operation: 'next',
              categoryDtoList: this.selectedOrder.categoryDtoList,
            })
              .then(response => {
                this.orderEditSubmitting = false
                let msg = this.$options.filters.message('order.update.success')
                this.$toasted.success(msg)
                $('#orderProcessPopup').modal('hide')
                this.loadOrders()
              })
              .catch(e => {
                this.orderEditSubmitting = false
              })
            return;
          }
          this.orderEditSubmitting = false
          var msg = this.$options.filters.error('common.form.validation.failed')
          this.$toasted.error(msg)
        });
      },
      cancelOrder: function (order) {
        var cancelUrl = '/s/userorder/update'

        this.$validator.validateAll().then((result) => {
          if (result) {
            this.submitting = true
            axios.post(cancelUrl, {
              orderId: order.orderId,
              operation: 'cancel',
              categoryDtoList: this.selectedOrder.categoryDtoList,
            })
              .then(response => {
                this.submitting = false
                let msg = this.$options.filters.message('order.cancel.success')
                this.$toasted.success(msg)
                this.loadOrders()
              })
              .catch(e => {
                this.submitting = false
              })
            return;
          }
          this.submitting = false
          var msg = this.$options.filters.error('common.form.validation.failed')
          this.$toasted.error(msg)
        });
      },
      payOrder(order) {
        let url = '/s/userorder/pay';
        let querystring = require('querystring');

        this.submitting = true
        axios.post(url, querystring.stringify({
          orderId: order.orderId
        }))
          .then(response => {
            this.submitting = false
            let msg = this.$options.filters.message('order.pay.success')
            this.$toasted.success(msg)
            this.loadOrders()
          })
          .catch(e => {
            this.submitting = false
          })
      },
      loadLogs(order) {

        this.setSelectedOrder(order);
        this.newLog = {};
        this.newLogDisplayed = true;

        let url = '/s/userorder/getlog';

        this.logLoaded = false;
        axios.get(url, {
          params: {
            orderId: this.selectedOrder.orderId,
          }
        })
          .then(response => {
            this.logLoaded = true;
            this.logItems = response.data;
          })
          .catch(e => {
            this.logLoaded = true;
          })
      },
      addLog() {
        if (!this.newLog.logTitle ||
            !this.newLog.logContent ||
            this.newLog.logTitle.length < 1 ||
            this.newLog.logContent.length < 1
        ){
          var msg = this.$options.filters.error('common.form.validation.failed');
          this.$toasted.error(msg);
          return;
        }
        let url = '/s/userorder/addlog';

        this.logSubmitting = true;

        axios.post(url, {
          logTitle: this.newLog.logTitle,
          logContent: this.newLog.logContent,
          orderId: this.selectedOrder.orderId
        })
          .then(response => {
            this.logSubmitting = false;
            this.loadLogs(this.selectedOrder);
          })
          .catch(e => {
            this.logSubmitting = false;
          })
      },
      logsClosed() {
        this.newLogDisplayed = false;
      },
      downloadFile(order) {
        let url = 's/userorder/download/' + order.orderId + '/' + order.uploadedFileName;
        axios({
          url: url,
          method: 'GET',
          responseType: 'blob', // important
        }).then((response) => {
          const url = window.URL.createObjectURL(new Blob([response.data]));
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', order.uploadedFileName);
          document.body.appendChild(link);
          link.click();
        });
      }
    }
  }
</script>

<style scoped>
  .logs-container {
    margin: 10px;
    overflow-y: auto;
  }
</style>

