CREATE INDEX idx_corprate_user_id ON core.mata_corporate_user (corprate_user_id);

CREATE INDEX idx_user_id ON core.mata_user (user_id);

CREATE INDEX idx_authentication_id ON core.mata_authentication (authentication_id);

CREATE INDEX idx_address_id ON core.mata_user_address (address_id);

CREATE INDEX idx_role_id ON core.mata_role (role_id);

CREATE INDEX idx_user_role_id ON core.mata_user_role (user_role_id);

CREATE INDEX idx_signup_request_id ON core.mata_signup_request (signup_request_id);

CREATE INDEX idx_order_category_id ON core.mata_order_category (order_category_id);

CREATE INDEX idx_order_id ON core.mata_order (order_id);

CREATE INDEX idx_message_id ON core.mata_message (message_id);

CREATE INDEX idx_offer_id ON core.mata_offer (offer_id);

CREATE INDEX idx_user_offer_id ON core.mata_user_offer (user_offer_id);

CREATE INDEX idx_notification_type_id ON core.mata_admin_notification_type (notification_type_id);

CREATE INDEX idx_notification_id ON core.mata_admin_notification (notification_id);

CREATE INDEX idx_authorization_id ON core.mata_user_authorization_code (id);

CREATE INDEX idx_notification_template_id ON core.mata_user_authorization_code (id);

CREATE INDEX idx_transaction_id ON core.mata_user_transaction (transaction_id);

CREATE INDEX idx_expire_date ON core.mata_admin_notification (expire_date);

CREATE INDEX idx_notification_type ON core.mata_admin_notification_type (notification_type);

CREATE INDEX idx_corportae_user_name_fa ON core.mata_corporate_user (corportae_user_name_fa);

CREATE INDEX idx_corportae_user_name_en ON core.mata_corporate_user (corportae_user_name_en);

CREATE INDEX idx_company_id ON core.mata_corporate_user (company_id);

CREATE INDEX idx_national_code ON core.mata_user (national_code);

CREATE INDEX idx_phone_number ON core.mata_user (phone_number);

CREATE INDEX idx_sender_user_id ON core.mata_message (sender_user_id);

CREATE INDEX idx_receiver_user_id ON core.mata_message (receiver_user_id);

CREATE INDEX idx_seen_status ON core.mata_message (seen_status);

CREATE INDEX idx_create_date ON core.mata_offer (create_date);

CREATE INDEX idx_offer_expire_date ON core.mata_offer (offer_expire_date);

CREATE INDEX idx_offer_status ON core.mata_offer (offer_status);

CREATE INDEX idx_parent_id ON core.mata_order_category (parent_id);

CREATE INDEX idx_address_user_id ON core.mata_user_address (user_id);

CREATE INDEX idx_authentication_user_id ON core.mata_authentication (user_id);

CREATE INDEX idx_user_name ON core.mata_authentication (user_name);

CREATE INDEX idx_password ON core.mata_authentication (password);

CREATE INDEX idx_user_type ON core.mata_user (user_type);

CREATE INDEX idx_authorization_user_id ON core.mata_user_authorization_code (user_id);

CREATE INDEX idx_credit_user_id ON core.mata_user_credit (user_id);

CREATE INDEX idx_first_name_fa ON core.mata_user (first_name_fa);

CREATE INDEX idx_last_name_fa ON core.mata_user (last_name_fa);

CREATE INDEX idx_status ON core.mata_user (status);

CREATE INDEX idx_order_create_date ON core.mata_order (create_date);

CREATE INDEX idx_order_status ON core.mata_order (order_status);

CREATE INDEX idx_order_user_id ON core.mata_order (user_id);

CREATE INDEX idx_signup_phone_number ON core.mata_signup_request (phone_number);

CREATE INDEX idx_signup_first_name_fa ON core.mata_signup_request (first_name_fa);

CREATE INDEX idx_signup_last_name_fa ON core.mata_signup_request (last_name_fa);

CREATE INDEX idx_signup_request_status ON core.mata_signup_request (signup_request_status);

CREATE INDEX idx_transaction_number ON core.mata_user_transaction (transaction_number);

CREATE INDEX idx_transaction_followup_number ON core.mata_user_transaction (transaction_followup_number);

